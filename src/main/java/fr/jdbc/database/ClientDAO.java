package fr.jdbc.database;

import fr.jdbc.models.Client;
import fr.jdbc.utils.DAOUtils;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

public class ClientDAO extends DAO<Client> {

    public ClientDAO() {
        super(Client.class);
    }

    public void updateName(EntityManager em, Client client, String newName) {
        DAOUtils.begin(em);
        client.setName(newName);
        DAOUtils.commit(em);
    }

    public void updateForename(EntityManager em, Client client, String newForename) {
        DAOUtils.begin(em);
        client.setForename(newForename);
        DAOUtils.commit(em);
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
