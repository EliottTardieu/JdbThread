package fr.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
                    App.getInstance().getClientsController().displayAll(entityManager);
                    break;

                case "2":
                    App.getInstance().getSuppliersController().displayAll(entityManager);
                    break;

                case "3":
                    App.getInstance().getSuppliesController().displayAll(entityManager);
                    break;

                case "4":
                    App.getInstance().getProductsController().displayAll(entityManager);
                    break;

                case "5":
                    App.getInstance().getOrderingsController().displayAll(entityManager);
                    break;

                case "6":
                    App.getInstance().getOrderingsController().initialize(entityManager);
                    break;

                case "7":
                    App.getInstance().getSuppliesController().initialize(entityManager);
                    break;

                case "8":
                    System.out.println("\t1) Ajouter un client\n"
                            + "\t2) Modifier un client\n"
                            + "\t3) Ajouter une reduction");
                    String clientChoice = scanner.nextLine();
                    switch (clientChoice) {
                        case "1":
                            App.getInstance().getClientsController().createClient(entityManager);
                            break;

                        case "2":
                            App.getInstance().getClientsController().modifiyClient(entityManager);
                            break;

                        case "3":
                            App.getInstance().getClientsController().updateDiscount(entityManager);
                            break;
                    }
                    break;

                case "9":
                    System.out.println("\t1) Ajouter un fournisseur\n"
                            + "\t2) Modifier un fournisseur");
                    String supplierChoice = scanner.nextLine();
                    switch (supplierChoice) {
                        case "1":
                            App.getInstance().getSuppliersController().initialize(entityManager);
                            break;

                        case "2":
                            App.getInstance().getSuppliersController().modifySupplier(entityManager);
                            break;
                    }
                    break;

                case "10":
                    System.out.println("\t1) Ajouter un produit\n"
                            + "\t2) Modifier un produit");
                    String productChoice = scanner.nextLine();
                    switch (productChoice) {
                        case "1":
                            App.getInstance().getProductsController().initialize(entityManager);
                            break;

                        case "2":
                            App.getInstance().getProductsController().modifyProduct(entityManager);
                            break;
                    }
                    break;
            }
        }

        entityManager.close();
        entityManagerFactory.close();
        System.exit(0);
    }
}
