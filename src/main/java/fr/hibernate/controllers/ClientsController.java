package fr.hibernate.controllers;

import fr.hibernate.database.ClientDAO;
import fr.hibernate.models.Client;
import fr.hibernate.models.FullAddress;
import fr.hibernate.views.ClientView;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class ClientsController extends Controller<Client> {

    private final ClientDAO DAO = new ClientDAO();
    private final ClientView view = new ClientView();

    public ClientsController() {
        super(Client.class);
    }

    public void createClient(EntityManager em) {
        view.createClient(em);
    }

    public Client createClient(EntityManager em, String name, String forename, int discount, FullAddress address, boolean autoCommit) {
        Client client = new Client(name, forename, discount, address);
        address.setClient(client);
        return this.save(em, DAO, client, autoCommit);
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
        this.models.remove(client);
        DAO.remove(em, client);
    }

    public void displayAll(EntityManager em) {
        view.displayAll(em);
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
