package fr.jdbc.database;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.Ordering;
import fr.jdbc.models.Product;
import fr.jdbc.utils.DAOUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

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
