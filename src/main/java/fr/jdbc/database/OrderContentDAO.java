package fr.jdbc.database;

import fr.jdbc.models.OrderContent;
import fr.jdbc.utils.DAOUtils;
import javax.persistence.EntityManager;

public class OrderContentDAO extends DAO<OrderContent> {

    public OrderContentDAO() {
        super(OrderContent.class);
    }

    public void updateQuantity(EntityManager em, OrderContent orderContent, int newQuantity) {
        DAOUtils.begin(em);
        orderContent.setQuantity(newQuantity);
        DAOUtils.commit(em);
    }
}
