package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.FullAddress;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return em.createQuery("from FullAddress").getResultList();
    }

    public FullAddress findByFullAddress(EntityManager em, HashMap<String, Object> criterias) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<FullAddress> criteriaQuery = criteriaBuilder.createQuery(FullAddress.class);
        Root<FullAddress> fullAddressRoot = criteriaQuery.from(FullAddress.class);

        Predicate predicateForAddress = null;
        Predicate predicateForCity = null;

        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("address")) {
                predicateForAddress = criteriaBuilder.equal(fullAddressRoot.get("address"), entry.getValue());
            }

            if (entry.getKey().equalsIgnoreCase("city")) {
                predicateForCity = criteriaBuilder.equal(fullAddressRoot.get("city"), entry.getValue());
            }
        }

        Predicate finalPredicate = criteriaBuilder.and(predicateForAddress, predicateForCity);
        criteriaQuery.where(finalPredicate);
        List<FullAddress> fullAddresses = em.createQuery(criteriaQuery).getResultList();
        return fullAddresses.isEmpty() ? null : fullAddresses.get(0);
    }
}
