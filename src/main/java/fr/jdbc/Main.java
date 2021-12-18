package fr.jdbc;

import dnl.utils.text.table.TextTable;
import fr.jdbc.models.*;

import java.util.*;


public class Main {


    public static void main(String[] args) {

        System.out.println("\nBonjour, bienvenue sur votre interface de gestion.");
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.println("1) Afficher la liste des clients\n"
                             + "2) Afficher la liste des fournisseurs\n"
                             + "3) Afficher la liste des produits\n"
                             + "4) Afficher la liste des commandes\n"
                             + "5) Créer une commande\n"
                             + "Tapez exit si vous souhaitez sortir de l'interface");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String[] columnsClient = {"Id", "Nom", "Prénom", "Réduction", "Adresse", "Ville"};
                    ArrayList<ArrayList<Object>> dataClient = new ArrayList<>();
                    for (Client client : App.getInstance().getClientDAO().getAll()) {
                        dataClient = client.display(dataClient);
                    }
                    Object[][] formalizedDataClient = dataClient.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
                    TextTable clientTable = new TextTable(columnsClient, formalizedDataClient);
                    clientTable.printTable();
                    System.out.println("");
                    break;

                case "2":
                    String[] columnsSupplier = {"Id", "Nom", "Prénom", "Adresse", "Ville"};
                    ArrayList<ArrayList<Object>> dataSupplier = new ArrayList<>();

                    String[] columnsSupplierContent = {"Id", "Nom", "Catégorie", "Prix Unitaire"};
                    ArrayList<ArrayList<Object>> dataSupplierContent = new ArrayList<>();

                    for (Supplier supplier : App.getInstance().getSupplierDAO().getAll()) {
                        dataSupplier = supplier.display(dataSupplier);
                        for (int i = 0; i < supplier.getProducts().size(); i++) {
                            dataSupplierContent = supplier.displayContent(dataSupplierContent, i);
                        }
                    }

                    Object[][] formalizedDataSupplier = dataSupplier.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
                    TextTable supplierTable = new TextTable(columnsSupplier, formalizedDataSupplier);

                    Object[][] formalizedDataSupplierContent = dataSupplierContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
                    TextTable supplierContentTable = new TextTable(columnsSupplierContent, formalizedDataSupplierContent);

                    System.out.println("Voulez-vous voir la liste des produits provenant des fournisseurs également ? (Oui/Non)");
                    String answerSupplier = scanner.nextLine();
                    if (answerSupplier.equalsIgnoreCase("oui")) {
                        supplierTable.printTable();
                        supplierContentTable.printTable();
                    } else if (answerSupplier.equalsIgnoreCase("non")) {
                        supplierTable.printTable();
                    }
                    System.out.println("");
                    break;

                case "3":
                    String[] columnsProducts = {"Id", "Nom", "Catégorie", "Espèce", "Prix Unitaire", "Stock"};
                    ArrayList<ArrayList<Object>> dataProducts = new ArrayList<>();
                    for (Product product : App.getInstance().getProductDAO().getAll()) {
                        dataProducts = product.display(dataProducts);
                    }
                    Object[][] formalizedDataProducts = dataProducts.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
                    TextTable productsTable = new TextTable(columnsProducts, formalizedDataProducts);
                    productsTable.printTable();
                    System.out.println("");
                    break;

                case "4":
                    String[] columnsOrders = {"Id", "Prix", "Nom Client", "Adresse", "Ville"};
                    ArrayList<ArrayList<Object>> dataOrders = new ArrayList<>();

                    String[] columnsOrderContent = {"Id", "Nom", "Quantité", "Prix Unitaire"};
                    ArrayList<ArrayList<Object>> dataOrderContent = new ArrayList<>();

                    for (Order order : App.getInstance().getOrderDAO().getAll()) {
                        dataOrders = order.display(dataOrders);
                        for (int i = 0; i < order.getProducts().size(); i++) {
                            dataOrderContent = order.displayContent(dataOrderContent, i);
                        }
                    }

                    Object[][] formalizedDataOrders = dataOrders.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
                    TextTable ordersTable = new TextTable(columnsOrders, formalizedDataOrders);

                    Object[][] formalizedDataOrderContent = dataOrderContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
                    TextTable orderContentTable = new TextTable(columnsOrderContent, formalizedDataOrderContent);

                    System.out.println("Voulez-vous les produits, la quantité acheté et leur prix en plus de la liste des commandes ? (Oui/Non)");
                    String answerOrder = scanner.nextLine();
                    if (answerOrder.equalsIgnoreCase("oui")) {
                        ordersTable.printTable();
                        orderContentTable.printTable();
                    } else if (answerOrder.equalsIgnoreCase("non")) {
                        ordersTable.printTable();
                    }
                    System.out.println("");
                    break;

                case "5":

                    break;
            }

        }

        System.exit(0);
    }
}
