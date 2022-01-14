package fr.jdbc.database;

import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class ClientDAO {

    public ClientDAO() {

    }

    public void save(EntityManager em, Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public void updateName(EntityManager em, Client client, String newName) {
        em.getTransaction().begin();
        client.setName(newName);
        em.getTransaction().commit();
    }

    public void updateForename(EntityManager em, Client client, String newForename) {
        em.getTransaction().begin();
        client.setForename(newForename);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, Client client) {
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
    }

    public List<Client> getAll(EntityManager em) {
        return em.createQuery("from Client").getResultList();
    }
}
