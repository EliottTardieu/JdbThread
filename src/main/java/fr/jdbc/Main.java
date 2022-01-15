package fr.jdbc;

import fr.jdbc.models.*;
import fr.jdbc.utils.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("s7_hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

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
                    App.getInstance().getClientView().displayAllClients(entityManager);
                    break;

                case "2":
                    App.getInstance().getSupplierView().displayAllSuppliers(entityManager);
                    break;

                case "3":
                    App.getInstance().getSupplyView().displayAllSupplies(entityManager);
                    break;

                case "4":
                    App.getInstance().getProductView().displayAllProducts(entityManager);
                    break;

                case "5":
                    App.getInstance().getOrderingView().displayAllOrders(entityManager);
                    break;

                case "6":
                    App.getInstance().getOrderingView().initialize(entityManager);
                    break;

                case "7":
                    App.getInstance().getSupplyView().initialize(entityManager);
                    break;

                case "8":
                    System.out.println("\t1) Ajouter un client\n"
                            + "\t2) Modifier un client\n"
                            + "\t3) Ajouter une reduction\n"
                            + "\t4) Supprimer un client");
                    String clientChoice = scanner.nextLine();
                    switch (clientChoice) {
                        case "1":
                            App.getInstance().getClientView().initialize(entityManager);
                            break;

                        case "2":
                            System.out.println("Entrez le nom du client que vous voulez modifier: ");
                            App.getInstance().getClientView().displayAllClients(entityManager);
                            String modifiedClientSc = scanner.nextLine();
                            HashMap<String, Object> criteriasModifiedClient = new HashMap<>();
                            criteriasModifiedClient.put("name", modifiedClientSc);
                            if (App.getInstance().getClientDAO().findByName(entityManager, criteriasModifiedClient) != null) {
                                Client modifiedClient = App.getInstance().getClientDAO().findByName(entityManager, criteriasModifiedClient);
                                System.out.println("\t1) Modifier le nom\n"
                                        + "\t2) Modifier le prénom");
                                String modifyClient = scanner.nextLine();
                                switch (modifyClient) {
                                    case "1":
                                        System.out.println("Entrez le nouveau Nom du client: ");
                                        App.getInstance().getClientController().updateName(entityManager, modifiedClient, scanner.nextLine());
                                        break;
                                    case "2":
                                        System.out.println("Entrez le nouveau Prénom du client: ");
                                        App.getInstance().getClientController().updateForename(entityManager, modifiedClient, scanner.nextLine());
                                        break;
                                }
                            } else {
                                Logger.severe("Unable to find such client.");
                            }
                            break;

                        case "3":
                            App.getInstance().getClientView().displayAllClients(entityManager);
                            System.out.println("Entrez le nom du client: ");
                            String clientName = scanner.nextLine();
                            System.out.println("Entrez le prénom du client: ");
                            String clientForename = scanner.nextLine();
                            HashMap<String, Object> criteriasClientR = new HashMap<>();
                            criteriasClientR.put("name", clientName);
                            if (App.getInstance().getClientDAO().findByName(entityManager, criteriasClientR) != null) {
                                Client client = App.getInstance().getClientDAO().findByName(entityManager, criteriasClientR);
                                System.out.println("Entrez le montant de la réduction: ");
                                int reduction = scanner.nextInt();
                                scanner.nextLine();
                                App.getInstance().getClientController().addDiscount(entityManager, client, reduction);
                                System.out.println("Reduction bien appliquée à " + client.getName() + "\n");
                                Logger.fine("Client reduction applied.");
                            } else {
                                Logger.severe("Unable to find client.");
                            }
                            break;

                        case "4":
                            // TODO: Supprimer le client et les informations le concernant
                            System.out.println("Entrez le nom du client que vous voulez supprimer: ");
                            App.getInstance().getClientView().displayAllClients(entityManager);
                            String removedClientName = scanner.nextLine();
                            HashMap<String, Object> criteriasClient = new HashMap<>();
                            criteriasClient.put("name", removedClientName);
                            if (App.getInstance().getClientDAO().findByName(entityManager, criteriasClient) != null) {
                                App.getInstance().getClientController().deleteClient(entityManager, App.getInstance().getClientDAO().findByName(entityManager, criteriasClient));
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
                            App.getInstance().getSupplierView().initialize(entityManager);
                            break;

                        case "2":
                            System.out.println("Entrez le nom du fournisseur que vous voulez modifier: ");
                            App.getInstance().getSupplierView().displayAllSuppliers(entityManager);
                            String modifiedSupplierSc = scanner.nextLine();
                            HashMap<String, Object> criteriasModifiedSupplier = new HashMap<>();
                            criteriasModifiedSupplier.put("name", modifiedSupplierSc);
                            if (App.getInstance().getSupplierDAO().findByName(entityManager, criteriasModifiedSupplier) != null) {
                                Supplier modifiedSupplier = App.getInstance().getSupplierDAO().findByName(entityManager, criteriasModifiedSupplier);
                                System.out.println("\t1) Modifier le nom\n"
                                        + "\t2) Modifier le prénom");
                                String modifySupplier = scanner.nextLine();
                                switch (modifySupplier) {
                                    case "1":
                                        System.out.println("Entrez le nouveau Nom du fournisseur: ");
                                        App.getInstance().getSupplierController().updateName(entityManager, modifiedSupplier, scanner.nextLine());
                                        break;
                                    case "2":
                                        System.out.println("Entrez le nouveau Prénom du fournisseur: ");
                                        App.getInstance().getSupplierController().updateForename(entityManager, modifiedSupplier, scanner.nextLine());
                                        break;
                                }
                            } else {
                                Logger.severe("Unable to find such supplier.");
                            }
                            break;

                        case "3":
                            // TODO: Supprimer les bonnes infos
                            System.out.println("Entrez le nom du fournisseur que vous voulez supprimer: ");
                            App.getInstance().getSupplierView().displayAllSuppliers(entityManager);
                            String removedSupplierName = scanner.nextLine();
                            HashMap<String, Object> criteriasSupplier = new HashMap<>();
                            criteriasSupplier.put("name", removedSupplierName);
                            if (App.getInstance().getSupplierDAO().findByName(entityManager, criteriasSupplier) != null) {
                                App.getInstance().getSupplierController().deleteSupplier(entityManager, App.getInstance().getSupplierDAO().find(entityManager, criteriasSupplier));
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
                            App.getInstance().getProductView().initialize(entityManager);
                            break;

                        case "2":
                            System.out.println("Entrez le nom du produit que vous voulez modifier: ");
                            App.getInstance().getProductView().displayAllProducts(entityManager);
                            String modifiedProductName = scanner.nextLine();
                            HashMap<String, Object> criteriasModifiedProduct = new HashMap<>();
                            criteriasModifiedProduct.put("name", modifiedProductName);
                            if (App.getInstance().getProductDAO().findByName(entityManager, criteriasModifiedProduct) != null) {
                                Product modifiedProduct = App.getInstance().getProductDAO().findByName(entityManager, criteriasModifiedProduct);
                                System.out.println("\t1) Modifier le nom\n"
                                        + "\t2) Modifier le prix unitaire\n"
                                        + "\t3) Modifier la quantité en stock");
                                String modifyObject = scanner.nextLine();
                                switch (modifyObject) {
                                    case "1":
                                        System.out.println("Entrer le nouveau nom: ");
                                        String newNameProduct = scanner.nextLine();
                                        App.getInstance().getProductController().updateName(entityManager, modifiedProduct, newNameProduct);
                                        System.out.println("Le nom du produit a bien été modifié\n");
                                        break;

                                    case "2":
                                        System.out.println("Entrer le nouveau prix: ");
                                        String newPriceProduct = scanner.nextLine();
                                        App.getInstance().getProductController().updatePrice(entityManager, modifiedProduct, Float.parseFloat(newPriceProduct));
                                        System.out.println("Le prix du produit a bien été modifié\n");
                                        break;

                                    case "3":
                                        System.out.println("Entrer la nouvelle quantité en stock: ");
                                        String newQuantityProduct = scanner.nextLine();
                                        App.getInstance().getProductController().updateQuantity(entityManager, modifiedProduct, Integer.parseInt(newQuantityProduct));
                                        System.out.println("La quantité en stock du produit a bien été modifié\n");
                                        break;
                                }
                            } else {
                                Logger.severe("Unable to find such product.");
                            }
                            break;

                        case "3":
                            // TODO: Supprimer les bonnes infos
                            //System.out.println("ATTENTION: Les produits que vous allez supprimer ici pourront impacter le reste de l'application, à n'utiliser qu'en connaissance de cause.");
                            System.out.println("Entrez le nom du produit que vous voulez supprimer: (stop pour annuler)");
                            App.getInstance().getProductView().displayAllProducts(entityManager);
                            String removedProductName = scanner.nextLine();
                            if (!removedProductName.equalsIgnoreCase("stop")) {
                                HashMap<String, Object> criteriasProduct = new HashMap<>();
                                criteriasProduct.put("name", removedProductName);
                                if (App.getInstance().getProductDAO().findByName(entityManager, criteriasProduct) != null) {
                                    App.getInstance().getProductController().deleteProduct(entityManager, App.getInstance().getProductDAO().findByName(entityManager, criteriasProduct));
                                    System.out.println("Produit bien supprimé\n");
                                }
                            }
                    }
                    break;
            }
        }

        System.exit(0);
        entityManager.close();
        entityManagerFactory.close();
    }
}
