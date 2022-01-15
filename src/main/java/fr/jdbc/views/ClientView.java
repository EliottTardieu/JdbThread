package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.utils.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    /**
     * Methode pour créer un client initialisé à partir de saisie dans un terminal.
     *
     * @return Client initialisée
     */
    public Client initialize(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        String clientAddress;
        String clientCity;
        FullAddress fullAddress;
        String name;
        String forename;
        int discount;

        // Adresse du client
        System.out.println("Entrez l'adresse du client: ");
        clientAddress = scanner.nextLine();
        System.out.println("Entrez la ville du client: ");
        clientCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", clientAddress);
        criteriasAdd.put("city", clientCity);
        if (App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd) != null) {
            fullAddress = App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd);
            Logger.fine("Client address found.");
        } else {
            fullAddress = App.getInstance().getFullAddressController().createFullAddress(em, clientAddress, clientCity);
            Logger.warning("Client address unknown, added to database.");
        }

        // Nom et prénom
        System.out.println("Entrez le nom: ");
        name = scanner.nextLine();
        System.out.println("Entrez le prénom: ");
        forename = scanner.nextLine();

        // Reduction
        System.out.println("Entrez le montant de la réduction: ");
        discount = scanner.nextInt();
        scanner.nextLine();

        return App.getInstance().getClientController().createClient(em, name, forename, discount, fullAddress);
    }
}
