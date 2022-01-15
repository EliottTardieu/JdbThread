package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void updateQuantity(EntityManager em, Product product, int newQuantity) {
        em.getTransaction().begin();
        product.setAvailableQuantity(newQuantity);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    public List<Product> getAll(EntityManager em) {
        return em.createQuery("from Product").getResultList();
    }

    public List<Product> getByCategory(EntityManager em, String category) {
        Query query = em.createQuery("from Product where category = :category");
        query.setParameter("category", category);
        return query.getResultList();
    }

    public Product findByName(EntityManager em, HashMap<String, Object> criterias) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Predicate predicateForName = null;

        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("name")) {
                predicateForName = criteriaBuilder.equal(productRoot.get("name"), entry.getValue());
            }
        }

        criteriaQuery.where(predicateForName);
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            return null;
        }
    }
}
