package fr.jdbc;

import fr.jdbc.models.*;

import java.util.HashMap;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        System.out.println("\nBonjour, bienvenue sur votre interface de gestion.");
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit")) {
            System.out.println("1) Afficher la liste des clients\n"
                             + "2) Afficher la liste des fournisseurs\n"
                             + "3) Afficher la liste des fournitures\n"
                             + "4) Afficher la liste des produits\n"
                             + "5) Afficher la liste des commandes\n"
                             + "6) Créer une commande\n"
                             + "7) Créer une fourniture\n"
                             + "8) Gérer les clients\n"
                             + "9) Gérer les fournisseurs\n"
                             + "10) Gérer les produits\n"
                             + "Tapez exit si vous souhaitez sortir de l'interface");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    App.getInstance().displayAllClients();
                    break;

                case "2":
                    App.getInstance().displayAllSuppliers();
                    break;

                case "3":
                    App.getInstance().displayAllSupplies();
                    break;

                case "4":
                    App.getInstance().displayAllProducts();
                    break;

                case "5":
                    App.getInstance().displayAllOrders();
                    break;

                case "6":
                    Order currentOrder = new Order().initialize();
                    break;

                case "7":
                    break;

                case "8":
                    System.out.println("\t1) Ajouter un client\n"
                                     + "\t2) Modifier un client\n"
                                     + "\t3) Supprimer un client");
                    String clientChoice = scanner.nextLine();
                    switch (clientChoice) {
                        case "1":
                            break;

                        case "2":
                            break;

                        case "3":
                            System.out.println("Entrez le nom du client que vous voulez supprimer : ");
                            App.getInstance().displayAllClients();
                            String removedClientName = scanner.nextLine();
                            HashMap<String, Object> criteriasClient = new HashMap<>();
                            criteriasClient.put("nom", removedClientName);
                            if (App.getInstance().getClientDAO().find(criteriasClient) != null) {
                                App.getInstance().getClientDAO().delete(App.getInstance().getClientDAO().find(criteriasClient));
                                System.out.println("Client bien supprimé\n");
                            }
                            break;
                    }
                    break;

                case "9":
                    System.out.println("\t1) Ajouter un fournisseur\n"
                                     + "\t2) Modifier un fournisseur\n"
                                     + "\t3) Supprimer un fournisseur");
                    String supplierChoice = scanner.nextLine();
                    switch (supplierChoice) {
                        case "1":
                            break;

                        case "2":
                            break;

                        case "3":
                            System.out.println("Entrez le nom du fournisseur que vous voulez supprimer : ");
                            App.getInstance().displayAllSuppliers();
                            String removedSupplierName = scanner.nextLine();
                            HashMap<String, Object> criteriasSupplier = new HashMap<>();
                            criteriasSupplier.put("nom", removedSupplierName);
                            if (App.getInstance().getSupplierDAO().find(criteriasSupplier) != null) {
                                App.getInstance().getSupplierDAO().delete(App.getInstance().getSupplierDAO().find(criteriasSupplier));
                                System.out.println("Fournisseur bien supprimé\n");
                            }
                            break;
                    }
                    break;

                case "10":
                    System.out.println("\t1) Ajouter un produit\n"
                                     + "\t2) Modifier un produit\n"
                                     + "\t3) Supprimer un produit");
                    String productChoice = scanner.nextLine();
                    switch (productChoice) {
                        case "1":
                            Product currentProduct = new Product().initialize();
                            break;

                        case "2":
                            System.out.println("\t1) Modifier le nom\n"
                                             + "\t2) Modifier le prix unitaire\n"
                                             + "\t3) Modifier la quantité en stock");
                            break;

                        case "3":
                            System.out.println("Entrez le nom du produit que vous voulez supprimer : ");
                            App.getInstance().displayAllProducts();
                            String removedProductName = scanner.nextLine();
                            HashMap<String, Object> criteriasProduct = new HashMap<>();
                            criteriasProduct.put("nom", removedProductName);
                            if (App.getInstance().getProductDAO().find(criteriasProduct) != null) {
                                App.getInstance().getProductDAO().delete(App.getInstance().getProductDAO().find(criteriasProduct));
                                System.out.println("Produit bien supprimé\n");
                            }
                    }

                    break;
            }

        }

        System.exit(0);
    }
}
