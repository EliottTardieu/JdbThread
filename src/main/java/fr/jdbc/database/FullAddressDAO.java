package fr.jdbc.database;

import fr.jdbc.models.FullAddress;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class FullAddressDAO {

    public FullAddressDAO() {

    }

    public void save(EntityManager em, FullAddress fullAddress) {
        em.getTransaction().begin();
        em.persist(fullAddress);
        em.getTransaction().commit();
    }

    public void updateAddress(EntityManager em, FullAddress fullAddress, String newAddress) {
        em.getTransaction().begin();
        fullAddress.setAddress(newAddress);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, FullAddress fullAddress) {
        em.getTransaction().begin();
        em.remove(fullAddress);
        em.getTransaction().commit();
    }

    public List<FullAddress> getAll(EntityManager em) {
        return em.createQuery("from fullAddress").getResultList();
    }
}
