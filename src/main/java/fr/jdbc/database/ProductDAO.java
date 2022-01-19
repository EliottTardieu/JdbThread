package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.Product;
import fr.jdbc.utils.DAOUtils;

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

public class ProductDAO extends DAO<Product> {

    public ProductDAO() {
        super(Product.class);
    }

    public void updateProductName(EntityManager em, Product product, String newName) {
        DAOUtils.begin(em);
        product.setName(newName);
        DAOUtils.commit(em);
    }

    public void updateProductPrice(EntityManager em, Product product, float newPrice) {
        DAOUtils.begin(em);
        product.setUnitPrice(newPrice);
        DAOUtils.commit(em);
    }

    public void updateQuantity(EntityManager em, Product product, int newQuantity) {
        DAOUtils.begin(em);
        product.setAvailableQuantity(newQuantity);
        DAOUtils.commit(em);
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
