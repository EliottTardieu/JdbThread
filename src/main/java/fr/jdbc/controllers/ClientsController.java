package fr.jdbc.controllers;

import fr.jdbc.database.ClientDAO;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.views.ClientView;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientsController {

    @Getter
    private final ArrayList<Client> models = new ArrayList<>();
    private final ClientDAO DAO = new ClientDAO();
    private final ClientView view = new ClientView();

    public ClientsController() {}

    public void createClient(EntityManager em) {
        view.createClient(em);
    }

    public Client createClient(EntityManager em, String name, String forename, int discount, FullAddress address, boolean autoCommit) {
        Client client = new Client(name, forename, discount, address);
        address.setClient(client);

        if (autoCommit) {
            DAO.save(em, client);
        } else {
            DAO.persist(em, client);
        }

        if (em.contains(client)) {
            return client;
        } else {
            return null;
        }
    }

    public void addDiscount(EntityManager em, Client client, int discount) {
        client.setDiscount(discount);
        DAO.save(em, client);
    }

    public void updateName(EntityManager em, Client client, String newName) {
        DAO.updateName(em, client, newName);
    }

    public void updateForename(EntityManager em, Client client, String newForename) {
        DAO.updateForename(em, client, newForename);
    }

    public void updateDiscount(EntityManager em) {
        view.updateDiscount(em);
    }

    public void modifiyClient(EntityManager em) {
        view.modifyClient(em);
    }

    public void deleteClient(EntityManager em, Client client) {
        DAO.remove(em, client);
    }

    public void displayAll(EntityManager em) {
        view.displayAllClients(em);
    }

    public List<Client> getAll(EntityManager em) {
        return DAO.getAll(em);
    }

    public Client find(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.find(em, criterias);
    }

    public Client findByName(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.findByName(em, criterias);
    }
}
