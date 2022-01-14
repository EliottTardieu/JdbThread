package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.OrderContent;
import fr.jdbc.models.Ordering;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderingView {
    public OrderingView() {

    }

    /**
     * Affiche la liste de toutes les commandes
     */
    public void displayAllOrders() {
        String[] columnsOrders = {"Id", "Prix", "Nom Client", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataOrders = new ArrayList<>();

        String[] columnsOrderContent = {"Id Commande", "Nom Produit", "Quantité", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataOrderContent = new ArrayList<>();

        for (Ordering ordering : App.getInstance().getOrderingDAO().getAll(App.getInstance().getEm())) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(ordering.getId());
            toAdd.add(ordering.getPrice());
            toAdd.add(ordering.getClient().getName());
            toAdd.add(ordering.getClient().getAddress().getAddress());
            toAdd.add(ordering.getClient().getAddress().getCity());
            dataOrders.add(toAdd);
        }

        for (OrderContent orderContent : App.getInstance().getOrderContentDAO().getAll(App.getInstance().getEm())) {
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
}
