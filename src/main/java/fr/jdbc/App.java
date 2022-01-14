package fr.jdbc;

import dnl.utils.text.table.TextTable;
import fr.jdbc.database.*;
import fr.jdbc.models.*;
import fr.jdbc.utils.Logger;
import lombok.Getter;

import java.util.*;

public class App {

    public static final float TVA = 1.15f;
    private static App instance;

    @Getter
    private final ClientDAO clientDAO = new ClientDAO();
    @Getter
    private final FullAddressDAO fullAddressDAO = new FullAddressDAO();
    @Getter
    private final OrderContentDAO orderContentDAO = new OrderContentDAO();
    @Getter
    private final OrderingDAO orderingDAO = new OrderingDAO();
    @Getter
    private final ProductDAO productDAO = new ProductDAO();
    @Getter
    private final SupplierDAO supplierDAO = new SupplierDAO();
    @Getter
    private final SupplyDAO supplyDAO = new SupplyDAO();

    private App() {
    }

    public static App getInstance() {
        if (instance == null) return new App();
        return instance;
    }

    /**
     * Affiche la liste de tous les clients
     */
    /*
    public void displayAllClients() {
        String[] columnsClient = {"Id", "Nom", "Prénom", "Réduction", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataClient = new ArrayList<>();
        for (Client client : App.getInstance().getClientDAO().getAll()) {
            dataClient = client.display(dataClient);
        }
        Object[][] formalizedDataClient = dataClient.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable clientTable = new TextTable(columnsClient, formalizedDataClient);
        clientTable.printTable();
    }
    */

    /**
     * Affiche la liste de tous les fournisseurs
     */
    /*
    public void displayAllSuppliers() {
        String[] columnsSupplier = {"Id", "Nom", "Prénom", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataSupplier = new ArrayList<>();

        String[] columnsSupplierContent = {"Id Fournisseur", "Nom", "Catégorie", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataSupplierContent = new ArrayList<>();

        for (Supplier supplier : App.getInstance().getSupplierDAO().getAll()) {
            dataSupplier = supplier.display(dataSupplier);
            dataSupplierContent = supplier.displayContent(dataSupplierContent);
        }

        Object[][] formalizedDataSupplier = dataSupplier.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplierTable = new TextTable(columnsSupplier, formalizedDataSupplier);

        Object[][] formalizedDataSupplierContent = dataSupplierContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplierContentTable = new TextTable(columnsSupplierContent, formalizedDataSupplierContent);

        System.out.println("Voulez-vous voir la liste des produits provenant des fournisseurs également ? (Oui/Non)");
        Scanner scanner = new Scanner(System.in);
        String answerSupplier = scanner.nextLine();
        if (answerSupplier.equalsIgnoreCase("oui")) {
            supplierTable.printTable();
            supplierContentTable.printTable();
        } else if (answerSupplier.equalsIgnoreCase("non")) {
            supplierTable.printTable();
        }
        System.out.println("");
    }
    */

    /**
     * Affiche la liste de tous les produits
     */
    /*
    public void displayAllProducts() {
        String[] columnsProducts = {"Id", "Nom", "Catégorie", "Espèce", "Prix Unitaire", "Stock Disponible"};
        ArrayList<ArrayList<Object>> dataProducts = new ArrayList<>();
        for (Product product : App.getInstance().getProductDAO().getAll()) {
            dataProducts = product.display(dataProducts);
        }
        Object[][] formalizedDataProducts = dataProducts.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable productsTable = new TextTable(columnsProducts, formalizedDataProducts);
        productsTable.printTable();
    }
    */

    /**
     * Affiche la liste de toutes les commandes
     */
    /*
    public void displayAllOrders() {
        String[] columnsOrders = {"Id", "Prix", "Nom Client", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataOrders = new ArrayList<>();

        String[] columnsOrderContent = {"Id Commande", "Nom Produit", "Quantité", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataOrderContent = new ArrayList<>();

        for (Order order : App.getInstance().getOrderDAO().getAll()) {
            dataOrders = order.display(dataOrders);
            dataOrderContent = order.displayContent(dataOrderContent);
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
    */

    /**
     * Affiche la liste de toutes les fournitures
     */
    /*
    public void displayAllSupplies() {
        String[] columnsSupply = {"Id", "Nom", "Prénom", "Prix"};
        ArrayList<ArrayList<Object>> dataSupply = new ArrayList<>();

        String[] columnsSupplyContent = {"Id Fourniture", "Nom", "Catégorie", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataSupplyContent = new ArrayList<>();

        for (Supply supply : App.getInstance().getSupplyDAO().getAll()) {
            dataSupply = supply.display(dataSupply);
            dataSupplyContent = supply.displayContent(dataSupplyContent);
        }

        Object[][] formalizedDataSupply = dataSupply.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplyTable = new TextTable(columnsSupply, formalizedDataSupply);

        Object[][] formalizedDataSupplyContent = dataSupplyContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplyContentTable = new TextTable(columnsSupplyContent, formalizedDataSupplyContent);

        System.out.println("Voulez-vous voir la liste des produits associés aux fournitures également ? (Oui/Non)");
        Scanner scanner = new Scanner(System.in);
        String answerSupply = scanner.nextLine();
        if (answerSupply.equalsIgnoreCase("oui")) {
            supplyTable.printTable();
            supplyContentTable.printTable();
        } else if (answerSupply.equalsIgnoreCase("non")) {
            supplyTable.printTable();
        }
        System.out.println("");
    }
    */
}
