package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDAO {

    public ClientDAO() {

    }

    public void save(EntityManager em, Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public void updateName(EntityManager em, Client client, String newName) {
        em.getTransaction().begin();
        client.setName(newName);
        em.getTransaction().commit();
    }

    public void updateForename(EntityManager em, Client client, String newForename) {
        em.getTransaction().begin();
        client.setForename(newForename);
        em.getTransaction().commit();
    }

    public void remove(EntityManager em, Client client) {
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
    }

    public List<Client> getAll(EntityManager em) {
        return em.createQuery("from Client").getResultList();
    }

    /**
     * Méthode pour trouver un client ayant le nom, prénom et adresse passé en paramètre
     */
    public Client find(EntityManager em, HashMap<String, Object> criterias) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);

        Predicate predicateForName = null;
        Predicate predicateForForename = null;
        Predicate predicateForAddress = null;
        Predicate predicateForDiscount = null;

        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("name")) {
                predicateForName = criteriaBuilder.equal(clientRoot.get("name"), entry.getValue());
            }

            if (entry.getKey().equalsIgnoreCase("forename")) {
                predicateForForename = criteriaBuilder.equal(clientRoot.get("forename"), entry.getValue());
            }

            if (entry.getKey().equalsIgnoreCase("address")) {
                predicateForAddress = criteriaBuilder.equal(clientRoot.get("address"), entry.getValue());
            }

            if (entry.getKey().equalsIgnoreCase("discount")) {
                predicateForDiscount = criteriaBuilder.equal(clientRoot.get("discount"), entry.getValue());
            }
        }

        Predicate finalPredicate = criteriaBuilder.and(predicateForName, predicateForForename, predicateForAddress, predicateForDiscount);
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
    public Client findByName(EntityManager em, HashMap<String, Object> criterias) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);

        Predicate predicateForName = null;

        for (Map.Entry<String, Object> entry : criterias.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("name")) {
                predicateForName = criteriaBuilder.equal(clientRoot.get("name"), entry.getValue());
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
