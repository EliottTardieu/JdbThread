package fr.hibernate.database;

import fr.hibernate.models.Ordering;
import fr.hibernate.utils.DAOUtils;

import javax.persistence.EntityManager;

public class OrderingDAO extends DAO<Ordering> {

    public OrderingDAO() {
        super(Ordering.class);
    }

    public void updatePrice(EntityManager em, Ordering ordering, float newPrice) {
        DAOUtils.begin(em);
        ordering.setPrice(newPrice);
        DAOUtils.commit(em);
    }
}
