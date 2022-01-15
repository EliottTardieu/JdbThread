package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.Ordering;
import fr.jdbc.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class OrderingDAO {

    public OrderingDAO() {

    }

    public void save(EntityManager em, Ordering ordering) {
        em.getTransaction().begin();
        em.persist(ordering);
        em.getTransaction().commit();
    }

    public void updatePrice(EntityManager em, Ordering ordering, float newPrice) {
        em.getTransaction().begin();
        ordering.setPrice(newPrice);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, Ordering ordering) {
        em.getTransaction().begin();
        em.remove(ordering);
        em.getTransaction().commit();
    }

    public List<Ordering> getAll(EntityManager em) {
        return em.createQuery("from Ordering").getResultList();
    }

    public List<Ordering> getByClientName(EntityManager em, Client client) {
        Query query = em.createQuery("from Ordering where client.name = :name");
        query.setParameter("name", client.getName());
        return query.getResultList();
    }
}
