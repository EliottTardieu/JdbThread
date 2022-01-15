package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Client;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class ClientView {
    public ClientView() {

    }

    /**
     * Affiche la liste de tous les clients
     */
    public void displayAllClients(EntityManager em) {
        String[] columnsClient = {"Id", "Nom", "Prénom", "Réduction", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataClient = new ArrayList<>();
        for (Client client : App.getInstance().getClientDAO().getAll(em)) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(client.getId());
            toAdd.add(client.getName());
            toAdd.add(client.getForename());
            toAdd.add(client.getDiscount());
            toAdd.add(client.getAddress().getAddress());
            toAdd.add(client.getAddress().getCity());
            dataClient.add(toAdd);
        }
        Object[][] formalizedDataClient = dataClient.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable clientTable = new TextTable(columnsClient, formalizedDataClient);
        clientTable.printTable();
    }
}
