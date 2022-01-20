package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.*;
import fr.jdbc.utils.DAOUtils;
import fr.jdbc.utils.Logger;
import javax.persistence.EntityManager;
import java.util.*;

public class OrderingView {

    public OrderingView() {

    }

    /**
     * Affiche la liste de toutes les commandes
     */
    public void displayAllOrders(EntityManager em) {
        String[] columnsOrders = {"Id", "Prix", "Nom Client", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataOrders = new ArrayList<>();

        String[] columnsOrderContent = {"Id Commande", "Nom Produit", "Quantité", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataOrderContent = new ArrayList<>();

        for (Ordering ordering : App.getInstance().getOrderingsController().getAll(em)) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(ordering.getId());
            toAdd.add(ordering.getPrice());
            toAdd.add(ordering.getClient().getName());
            toAdd.add(ordering.getClient().getAddress().getAddress());
            toAdd.add(ordering.getClient().getAddress().getCity());
            dataOrders.add(toAdd);
        }

        for (OrderContent orderContent : App.getInstance().getOrdersContentsController().getAll(em)) {
            ArrayList<Object> toAddContent = new ArrayList<>();
            toAddContent.add(orderContent.getOrdering().getId());
            toAddContent.add(orderContent.getProduct().getName());
            toAddContent.add(orderContent.getQuantity());
            toAddContent.add(orderContent.getProduct().getUnitPrice());
            dataOrderContent.add(toAddContent);
        }

        Object[][] formalizedDataOrders = dataOrders.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable ordersTable = new TextTable(columnsOrders, formalizedDataOrders);

        Object[][] formalizedDataOrderContent = dataOrderContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable orderContentTable = new TextTable(columnsOrderContent, formalizedDataOrderContent);

        System.out.println("Voulez-vous les produits, la quantité acheté et leur prix en plus de la liste des commandes ? (Oui/Non)");
        Scanner scanner = new Scanner(System.in);
        String answerOrder = scanner.nextLine();
        if (answerOrder.equalsIgnoreCase("oui")) {
            ordersTable.printTable();
            orderContentTable.printTable();
        } else if (answerOrder.equalsIgnoreCase("non")) {
            ordersTable.printTable();
        }
        System.out.println("");
    }

    /**
     * Methode pour créer une commande initialisée à partir de saisie dans un terminal.
     *
     * @return Commande initialisée
     */
    public Ordering initialize(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        Ordering ordering = App.getInstance().getOrderingsController().createEmptyOrdering();
        Set<OrderContent> orderContentSet = new HashSet<>();

        // Adresse du client
        System.out.println("Entrez l'adresse du client: ");
        String clientAddress = scanner.nextLine();
        System.out.println("Entrez la ville du client: ");
        String clientCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", clientAddress);
        criteriasAdd.put("city", clientCity);
        FullAddress clientFullAddress;
        if (App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd) != null) {
            clientFullAddress = App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd);
            Logger.fine("Client address found.");
        } else {
            clientFullAddress = App.getInstance().getFullAddressesController().createFullAddress(em, clientAddress, clientCity, true);
            Logger.warning("Client address unknown, added to database.");
        }

        // Nom du client et client
        System.out.println("Entrez le nom du client: ");
        String clientName = scanner.nextLine();
        System.out.println("Entrez le prénom du client: ");
        String clientForename = scanner.nextLine();
        HashMap<String, Object> criteriasCli = new HashMap<>();
        criteriasCli.put("name", clientName);
        criteriasCli.put("forename", clientForename);
        criteriasCli.put("address", clientFullAddress.getId());
        Client client;
        if (App.getInstance().getClientsController().find(em, criteriasCli) != null) {
            client = App.getInstance().getClientsController().find(em, criteriasCli);
        } else {
            System.out.println("Entrez la réduction du client: ");
            int clientDiscount = scanner.nextInt();
            scanner.nextLine();
            client = App.getInstance().getClientsController().createClient(em, clientName, clientForename, clientDiscount, clientFullAddress, true);
        }

        // Produits et Quantités
        boolean stop = false;
        System.out.println("Si vous avez fini, entrez \"stop\".");
        HashMap<Product, Integer> backupQuantity = new HashMap<>();
        for (Product product : App.getInstance().getProductsController().getAll(em)) {
            backupQuantity.put(product, product.getAvailableQuantity());
        }
        while (!stop) {
            App.getInstance().getProductsController().displayAll(em);
            System.out.println("Entrez le nom du produit à ajouter: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("stop")) {
                stop = true;
            } else {
                HashMap<String, Object> criteriasProd = new HashMap<>();
                criteriasProd.put("name", choice);
                if (App.getInstance().getProductsController().findByName(em, criteriasProd) != null) {
                    Product product = App.getInstance().getProductsController().findByName(em, criteriasProd);
                    System.out.println("Entrez une quantité pour ce produit: (stop quand vous avez fini)");
                    int qt = scanner.nextInt();
                    scanner.nextLine();
                    if (qt <= 0) {
                        System.out.println("Quantité invalide, sélectionnez une quantité entre 1 et " + product.getAvailableQuantity());
                    } else if (qt > product.getAvailableQuantity()) {
                        System.out.println("Quantité invalide, vous ne pouvez pas mettre plus de produits que ce que vous avez de disponible.");
                    } else {
                        orderContentSet.add(App.getInstance().getOrdersContentsController().createOrderContent(em, ordering, product, qt, true));
                    }
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }
        // Prix
        float price = App.getInstance().getOrderingsController().computePrice(orderContentSet, client);
        System.out.println("Le prix de la commande est de " + price + "€");

        Logger.fine("Order Saved in database.");
        ordering = App.getInstance().getOrderingsController().initializeOrdering(em, ordering, price, client, orderContentSet, true);
        System.out.println("Commande validée avec succès.");

        return ordering;
    }
}
