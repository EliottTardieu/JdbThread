package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;
import fr.jdbc.utils.DAOUtils;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierDAO extends DAO<Supplier> {

    public SupplierDAO() {
        super(Supplier.class);
    }

    public void updateName(EntityManager em, Supplier supplier, String newName) {
        em.getTransaction().begin();
        supplier.setName(newName);
        em.getTransaction().commit();
    }

    public void updateForename(EntityManager em, Supplier supplier, String newForename) {
        em.getTransaction().begin();
        supplier.setForename(newForename);
        em.getTransaction().commit();
    }

    /**
     * Méthode pour trouver un fournisseur ayant le nom, prénom et adresse passé en paramètre
     */
    public Supplier find(EntityManager em, HashMap<String, Object> criterias) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Supplier> criteriaQuery = criteriaBuilder.createQuery(Supplier.class);
        Root<Supplier> supplierRoot = criteriaQuery.from(Supplier.class);

        Predicate predicateForName = null;
        Predicate predicateForForename = null;
        Predicate predicateForAddress = null;

        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("name")) {
                predicateForName = criteriaBuilder.equal(supplierRoot.get("name"), entry.getValue());
            }

            if (entry.getKey().equalsIgnoreCase("forename")) {
                predicateForForename = criteriaBuilder.equal(supplierRoot.get("forename"), entry.getValue());
            }

            if (entry.getKey().equalsIgnoreCase("address")) {
                predicateForAddress = criteriaBuilder.equal(supplierRoot.get("address"), entry.getValue());
            }
        }

        Predicate finalPredicate = criteriaBuilder.and(predicateForName, predicateForForename, predicateForAddress);
        criteriaQuery.where(finalPredicate);
        try {
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NullPointerException | NoResultException e) {
            return null;
        }
    }

    /**
     * Méthode pour trouver un client ayant le nom passé en paramètre
     */
    public Supplier findByName(EntityManager em, HashMap<String, Object> criterias) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Supplier> criteriaQuery = criteriaBuilder.createQuery(Supplier.class);
        Root<Supplier> supplierRoot = criteriaQuery.from(Supplier.class);

        Predicate predicateForName = null;

        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("name")) {
                predicateForName = criteriaBuilder.equal(supplierRoot.get("name"), entry.getValue());
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
