package fr.jdbc.database;

import fr.jdbc.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class ProductDAO {

    public ProductDAO() {

    }

    public void save(EntityManager em, Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void updateProductName(EntityManager em, Product product, String newName) {
        em.getTransaction().begin();
        product.setName(newName);
        em.getTransaction().commit();
    }

    public void updateProductPrice(EntityManager em, Product product, float newPrice) {
        em.getTransaction().begin();
        product.setUnitPrice(newPrice);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    public List<Product> getAll(EntityManager em) {
        return em.createQuery("from product").getResultList();
    }

    public List<Product> getByCategory(EntityManager em, String category) {
        Query query = em.createQuery("from product where category = :category");
        query.setParameter("category", category);
        return query.getResultList();
    }
}
