package fr.jdbc;

import dnl.utils.text.table.TextTable;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;

import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        /* It's working !
        String[] columnsClient = {"Id", "Nom", "Prénom", "Réduction", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataClient = new ArrayList<>();
        for (Client c : App.getInstance().getClientDAO().getAll()) {
            dataClient = c.display(dataClient);
        }
        Object[][] formalizedDataClient = dataClient.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable clientTable = new TextTable(columnsClient, formalizedDataClient);
        clientTable.printTable();
        */

        //TODO Terminer cet affichage !
        /*
        String[] columnsSupplier = {"Id", "Nom", "Prénom", "Adresse", "Ville", "Produits"};
        ArrayList<ArrayList<Object>> dataSupplier = new ArrayList<>();
        for (Supplier supplier : App.getInstance().getSupplierDAO().getAll()) {
            dataSupplier = supplier.display(dataSupplier);
        }
        Object[][] formalizedDataSupplier = dataSupplier.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplierTable = new TextTable(columnsSupplier, formalizedDataSupplier);
        supplierTable.printTable();
        */

         
        /* It's working !
        String[] columnsOrderContent = {"Nom Produit", "Quantité"};
        ArrayList<ArrayList<Object>> dataOrderContent = new ArrayList<>();
        for (int i = 0; i < App.getInstance().getOrderDAO().getAll().get(0).getProducts().size(); i++) {
            dataOrderContent = App.getInstance().getOrderDAO().getAll().get(0).displayContent(dataOrderContent, i);
        }
        Object[][] formalizedDataOrderContent = dataOrderContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable orderContentTable = new TextTable(columnsOrderContent, formalizedDataOrderContent);
        orderContentTable.printTable();
        */

        System.exit(0);
    }
}
