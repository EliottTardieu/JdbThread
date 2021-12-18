package fr.jdbc;

import fr.jdbc.models.*;

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
                             + "6) Gérer les fournisseurs\n"
                             + "6) Gérer les produits\n"
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
            }

        }

        System.exit(0);
    }
}
