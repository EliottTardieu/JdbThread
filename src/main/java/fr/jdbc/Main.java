package fr.jdbc;

import fr.jdbc.models.*;
import fr.jdbc.utils.Logger;

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
                    Supply currentSupply = new Supply().initialize();
                    break;

                case "8":
                    System.out.println("\t1) Ajouter un client\n"
                                     + "\t2) Modifier un client\n"
                                     + "\t3) Ajouter une reduction\n"
                                     + "\t4) Supprimer un client");
                    String clientChoice = scanner.nextLine();
                    switch (clientChoice) {
                        case "1":
                            Client currentClient = new Client().initialize();
                            break;

                        case "2":
                            System.out.println("\t1) Modifier le nom\n"
                                             + "\t2) Modifier le prénom");
                            break;

                        case "3":
                            System.out.println("Entrez le nom du client: ");
                            String clientName = scanner.nextLine();
                            System.out.println("Entrez le prénom du client: ");
                            String clientForename = scanner.nextLine();
                            HashMap<String, Object> criteriasClientR = new HashMap<>();
                            criteriasClientR.put("nom", clientName);
                            criteriasClientR.put("prenom", clientForename);
                            if (App.getInstance().getClientDAO().find(criteriasClientR) != null) {
                                Client client = App.getInstance().getClientDAO().find(criteriasClientR);
                                System.out.println("Entrez le montant de la réduction: ");
                                int reduction = scanner.nextInt();
                                scanner.nextLine();
                                client.setDiscount(reduction);
                                System.out.println("Reduction bien appliquée à "+ client.getName()+"\n");
                                Logger.fine("Client reduction applied.");
                            } else {
                                Logger.severe("Unable to find client.");
                            }
                            break;

                        case "4":
                            System.out.println("Entrez le nom du client que vous voulez supprimer: ");
                            App.getInstance().displayAllClients();
                            String removedClientName = scanner.nextLine();
                            HashMap<String, Object> criteriasClient = new HashMap<>();
                            criteriasClient.put("nom", removedClientName);
                            if (App.getInstance().getClientDAO().find(criteriasClient) != null) {
                                App.getInstance().getClientDAO().delete(App.getInstance().getClientDAO().find(criteriasClient));
                                System.out.println("Client bien supprimé\n");
                                Logger.fine("Client removed.");
                            } else {
                                Logger.severe("Unable to remove client.");
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
                            Supplier supplier = new Supplier().initialize();
                            break;

                        case "2":
                            System.out.println("\t1) Modifier le nom\n"
                                             + "\t2) Modifier le prénom");
                            break;

                        case "3":
                            System.out.println("Entrez le nom du fournisseur que vous voulez supprimer: ");
                            App.getInstance().displayAllSuppliers();
                            String removedSupplierName = scanner.nextLine();
                            HashMap<String, Object> criteriasSupplier = new HashMap<>();
                            criteriasSupplier.put("nom", removedSupplierName);
                            if (App.getInstance().getSupplierDAO().find(criteriasSupplier) != null) {
                                App.getInstance().getSupplierDAO().delete(App.getInstance().getSupplierDAO().find(criteriasSupplier));
                                System.out.println("Fournisseur bien supprimé\n");
                                Logger.fine("Supplier removed.");
                            } else {
                                Logger.severe("Unable to remove supplier.");
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
                            System.out.println("Entrez le nom du produit que vous voulez modifier: ");
                            App.getInstance().displayAllProducts();
                            String modifiedProductName = scanner.nextLine();
                            HashMap<String, Object> criteriasModifiedProduct = new HashMap<>();
                            criteriasModifiedProduct.put("nom", modifiedProductName);
                            if (App.getInstance().getProductDAO().find(criteriasModifiedProduct) != null) {
                                Product modifiedProduct = App.getInstance().getProductDAO().find(criteriasModifiedProduct);
                                System.out.println("\t1) Modifier le nom\n"
                                                 + "\t2) Modifier le prix unitaire\n"
                                                 + "\t3) Modifier la quantité en stock");
                                String modifyObject = scanner.nextLine();
                                switch (modifyObject) {
                                    case "1":
                                        System.out.println("Entrer le nouveau nom: ");
                                        String newNameProduct = scanner.nextLine();
                                        modifiedProduct.setName(newNameProduct);
                                        App.getInstance().getProductDAO().save(modifiedProduct);
                                        System.out.println("Le nom du produit a bien été modifié");
                                        break;

                                    case "2":
                                        System.out.println("Entrer le nouveau prix: ");
                                        String newPriceProduct = scanner.nextLine();
                                        modifiedProduct.setUnitPrice(Float.parseFloat(newPriceProduct));
                                        App.getInstance().getProductDAO().save(modifiedProduct);
                                        System.out.println("Le prix du produit a bien été modifié");
                                        break;

                                    case "3":
                                        System.out.println("Entrer la nouvelle quantité en stock: ");
                                        String newQuantityProduct = scanner.nextLine();
                                        modifiedProduct.setAvailableQuantity(Integer.parseInt(newQuantityProduct));
                                        App.getInstance().getProductDAO().save(modifiedProduct);
                                        System.out.println("La quantité en stock du produit a bien été modifié");
                                        break;
                                }
                            }
                            break;

                        case "3":
                            System.out.println("ATTENTION: Les produits que vous allez supprimer ici pourront impacter le reste de l'application, à n'utiliser qu'en connaissance de cause.");
                            System.out.println("Entrez le nom du produit que vous voulez supprimer: (stop pour annuler)");
                            App.getInstance().displayAllProducts();
                            String removedProductName = scanner.nextLine();
                            if(!removedProductName.equalsIgnoreCase("stop")){
                                HashMap<String, Object> criteriasProduct = new HashMap<>();
                                criteriasProduct.put("nom", removedProductName);
                                if (App.getInstance().getProductDAO().find(criteriasProduct) != null) {
                                    App.getInstance().getProductDAO().delete(App.getInstance().getProductDAO().find(criteriasProduct));
                                    System.out.println("Produit bien supprimé\n");
                                }
                            }
                    }

                    break;
            }

        }

        System.exit(0);
    }
}
