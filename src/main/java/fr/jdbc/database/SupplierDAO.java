package fr.jdbc.database;

import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class SupplierDAO {

    public SupplierDAO() {

    }

    public void save(EntityManager em, Supplier supplier) {
        em.getTransaction().begin();
        em.persist(supplier);
        em.getTransaction().commit();
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

    public void remove(EntityManager em, Supplier supplier) {
        em.getTransaction().begin();
        em.remove(supplier);
        em.getTransaction().commit();
    }

    public List<Supplier> getAll(EntityManager em) {
        return em.createQuery("from Supplier").getResultList();
    }
}
