package fr.jdbc;

import dnl.utils.text.table.TextTable;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;

import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        String[] columnsClient = {"Id", "Nom", "Prénom", "Réduction", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataClient = new ArrayList<>();
        for (Client c : App.getInstance().getClientDAO().getAll()) {
            dataClient = c.display(dataClient);
        }
        Object[][] formalizedDataClient = dataClient.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable clientTable = new TextTable(columnsClient, formalizedDataClient);
        clientTable.printTable();

        System.exit(0);
    }
}
