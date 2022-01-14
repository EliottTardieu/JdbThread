package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.ClientDAO;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class ClientController {

    public Client createClient(EntityManager em, String name, String forename, FullAddress address, int discount) {
        Client client = new Client(name, forename, address, discount);
        address.setClient(client);

        App.getInstance().getClientDAO().save(em, client);

        if (em.contains(client)) {
            return client;
        } else {
            return null;
        }
    }

    public void updateName(EntityManager em, Client client, String newName) {
        App.getInstance().getClientDAO().updateName(em, client, newName);
    }

    public void updateForename(EntityManager em, Client client, String newForename) {
        App.getInstance().getClientDAO().updateForename(em, client, newForename);
    }

    public void deleteClient(EntityManager em, Client client) {
        App.getInstance().getClientDAO().remove(em, client);
    }
}
