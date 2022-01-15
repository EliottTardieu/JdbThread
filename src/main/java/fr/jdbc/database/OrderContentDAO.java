package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.OrderContent;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderContentDAO {

    public OrderContentDAO() {

    }

    public void save(EntityManager em, OrderContent orderContent) {
        em.getTransaction().begin();
        em.persist(orderContent);
        em.getTransaction().commit();
    }

    public void updateQuantity(EntityManager em, OrderContent orderContent, int newQuantity) {
        em.getTransaction().begin();
        orderContent.setQuantity(newQuantity);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, OrderContent orderContent) {
        em.getTransaction().begin();
        em.remove(orderContent);
        em.getTransaction().commit();
    }

    public List<OrderContent> getAll(EntityManager em) {
        return em.createQuery("from OrderContent").getResultList();
    }
}
