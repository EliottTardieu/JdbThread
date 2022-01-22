package fr.hibernate.views;

import dnl.utils.text.table.TextTable;
import fr.hibernate.App;
import fr.hibernate.models.Client;
import fr.hibernate.models.FullAddress;
import fr.hibernate.utils.Logger;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClientView implements View {

    public ClientView() {}

    /**
     * Affiche la liste de tous les clients
     */
    @Override
    public void displayAll(EntityManager em) {
        String[] columnsClient = {"Id", "Nom", "Prénom", "Réduction", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataClient = new ArrayList<>();
        for (Client client : App.getInstance().getClientsController().getAll(em)) {
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
     */
    public void createClient(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        // Adresse du client
        System.out.println("Entrez l'adresse du client: ");
        String clientAddress = scanner.nextLine();
        System.out.println("Entrez la ville du client: ");
        String clientCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", clientAddress);
        criteriasAdd.put("city", clientCity);
        FullAddress fullAddress;
        if (App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd) != null) {
            fullAddress = App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd);
            Logger.fine("Client address found.");
        } else {
            fullAddress = App.getInstance().getFullAddressesController().createFullAddress(em, clientAddress, clientCity, true);
            Logger.warning("Client address unknown, added to database.");
        }

        // Nom et prénom
        System.out.println("Entrez le nom: ");
        String name = scanner.nextLine();
        System.out.println("Entrez le prénom: ");
        String forename = scanner.nextLine();

        // Reduction
        System.out.println("Entrez le montant de la réduction: ");
        int discount = scanner.nextInt();
        scanner.nextLine();

        App.getInstance().getClientsController().createClient(em, name, forename, discount, fullAddress, true);
    }

    public void modifyClient(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom du client que vous voulez modifier: ");
        App.getInstance().getClientsController().displayAll(em);
        String modifiedClientSc = scanner.nextLine();
        HashMap<String, Object> criteriasModifiedClient = new HashMap<>();
        criteriasModifiedClient.put("name", modifiedClientSc);
        if (App.getInstance().getClientsController().findByName(em, criteriasModifiedClient) != null) {
            Client modifiedClient = App.getInstance().getClientsController().findByName(em, criteriasModifiedClient);
            System.out.println("\t1) Modifier le nom\n"
                    + "\t2) Modifier le prénom");
            String modifyClient = scanner.nextLine();
            switch (modifyClient) {
                case "1":
                    System.out.println("Entrez le nouveau Nom du client: ");
                    App.getInstance().getClientsController().updateName(em, modifiedClient, scanner.nextLine());
                    break;
                case "2":
                    System.out.println("Entrez le nouveau Prénom du client: ");
                    App.getInstance().getClientsController().updateForename(em, modifiedClient, scanner.nextLine());
                    break;
            }
        } else {
            Logger.severe("Unable to find such client.");
        }
    }

    public void updateDiscount(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        App.getInstance().getClientsController().displayAll(em);
        System.out.println("Entrez le nom du client: ");
        String clientName = scanner.nextLine();
        System.out.println("Entrez le prénom du client: ");
        String clientForename = scanner.nextLine();
        HashMap<String, Object> criteriasClientR = new HashMap<>();
        criteriasClientR.put("name", clientName);
        if (App.getInstance().getClientsController().findByName(em, criteriasClientR) != null) {
            Client client = App.getInstance().getClientsController().findByName(em, criteriasClientR);
            System.out.println("Entrez le montant de la réduction: ");
            int reduction = scanner.nextInt();
            scanner.nextLine();
            App.getInstance().getClientsController().addDiscount(em, client, reduction);
            System.out.println("Reduction bien appliquée à " + client.getName() + "\n");
            Logger.fine("Client reduction applied.");
        } else {
            Logger.severe("Unable to find client.");
        }
    }
}
