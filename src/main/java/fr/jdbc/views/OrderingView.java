package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.*;
import fr.jdbc.utils.Logger;
import org.hibernate.criterion.Order;

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

        for (Ordering ordering : App.getInstance().getOrderingDAO().getAll(em)) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(ordering.getId());
            toAdd.add(ordering.getPrice());
            toAdd.add(ordering.getClient().getName());
            toAdd.add(ordering.getClient().getAddress().getAddress());
            toAdd.add(ordering.getClient().getAddress().getCity());
            dataOrders.add(toAdd);
        }

        for (OrderContent orderContent : App.getInstance().getOrderContentDAO().getAll(em)) {
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
        String choice;
        Ordering ordering = new Ordering();
        Set<OrderContent> orderContentSet = new HashSet<>();
        Client client;
        String clientName;
        String clientForename;
        String clientAddress;
        String clientCity;
        int clientDiscount;
        FullAddress clientFullAddress;
        // Adresse du client
        System.out.println("Entrez l'adresse du client: ");
        clientAddress = scanner.nextLine();
        System.out.println("Entrez la ville du client: ");
        clientCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", clientAddress);
        criteriasAdd.put("city", clientCity);
        if (App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd) != null) {
            clientFullAddress = App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd);
            Logger.fine("Client address found.");
        } else {
            clientFullAddress = App.getInstance().getFullAddressController().createFullAddress(em, clientAddress, clientCity);
            Logger.warning("Client address unknown, added to database.");
        }
        // Nom du client et client
        System.out.println("Entrez le nom du client: ");
        clientName = scanner.nextLine();
        System.out.println("Entrez le prénom du client: ");
        clientForename = scanner.nextLine();
        System.out.println("Entrez la réduction du client: ");
        clientDiscount = scanner.nextInt();
        scanner.nextLine();
        HashMap<String, Object> criteriasCli = new HashMap<>();
        criteriasCli.put("name", clientName);
        criteriasCli.put("forename", clientForename);
        criteriasCli.put("address", clientFullAddress.getId());
        criteriasCli.put("discount", clientDiscount);
        if (App.getInstance().getClientDAO().find(em, criteriasCli) != null) {
            client = App.getInstance().getClientDAO().find(em, criteriasCli);
        } else {
            client = App.getInstance().getClientController().createClient(em, clientName, clientForename, clientDiscount, clientFullAddress);
        }
        // Produits et Quantités
        boolean stop = false;
        System.out.println("Si vous avez fini, entrez \"stop\".");
        /*
        for (Product product : App.getInstance().getProductDAO().getAll()) {
            this.backupQuantity.put(product, product.getAvailableQuantity());
        }*/
        while (!stop) {
            App.getInstance().getProductView().displayAllProducts(em);
            System.out.println("Entrez le nom du produit à ajouter: ");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("stop")) {
                stop = true;
            } else {
                HashMap<String, Object> criteriasProd = new HashMap<>();
                criteriasProd.put("name", choice);
                if (App.getInstance().getProductDAO().findByName(em, criteriasProd) != null) {
                    Product product = App.getInstance().getProductDAO().findByName(em, criteriasProd);
                    System.out.println("Entrez une quantité pour ce produit: (stop quand vous avez fini)");
                    int qt = scanner.nextInt();
                    scanner.nextLine();
                    if (qt <= 0) {
                        System.out.println("Quantité invalide, sélectionnez une quantité entre 1 et " + product.getAvailableQuantity());
                    } else if (qt > product.getAvailableQuantity()) {
                        System.out.println("Quantité invalide, vous ne pouvez pas mettre plus de produits que ce que vous avez de disponible.");
                    } else {
                        orderContentSet.add(App.getInstance().getOrderContentController().createOrderContent(em, ordering, product, qt));
                    }
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }
        // Prix
        float price = App.getInstance().getOrderingController().computePrice(orderContentSet, client);
        System.out.println("Le prix de la commande est de " + price + "€");

        System.out.println("Commande terminée, confirmer l'enregistrement ? ");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("oui") || choice.equalsIgnoreCase("yes")) {
            Logger.fine("Order Saved in database.");
            App.getInstance().getOrderingController().createOrdering(em, ordering, price, client, orderContentSet);
            System.out.println("Commande validée avec succès.");
        } else if (choice.equalsIgnoreCase("non") || choice.equalsIgnoreCase("no")) {
            Logger.warning("Order not saved, the content has been reset.");
            //this.reset();
            System.out.println("Commande annulée avec succès.");
        } else {
            Logger.severe("Unknown answer, order was reset by default.");
            //this.reset();
        }
        return ordering;
    }
}
