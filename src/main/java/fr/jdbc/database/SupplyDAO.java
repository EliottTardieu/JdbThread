package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Supplier;
import fr.jdbc.models.Supply;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class SupplyDAO {

    public SupplyDAO() {

    }

    public void save(EntityManager em, Supply supply) {
        em.getTransaction().begin();
        em.persist(supply);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, Supply supply) {
        em.getTransaction().begin();
        em.remove(supply);
        em.getTransaction().commit();
    }

    public List<Supply> getAll(EntityManager em) {
        return em.createQuery("from Supply").getResultList();
    }

    public List<Supply> getBySupplier(EntityManager em, Supplier supplier) {
        Query query = em.createQuery("from Supply where supplier = :supplier");
        query.setParameter("supplier", supplier);
        return query.getResultList();
    }
}
