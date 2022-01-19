package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.OrderContentDAO;
import fr.jdbc.models.OrderContent;
import fr.jdbc.models.Ordering;
import fr.jdbc.models.Product;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class OrdersContentsController {

    @Getter
    private final ArrayList<OrderContent> ordersContents = new ArrayList<>();
    private final OrderContentDAO DAO = new OrderContentDAO();

    public OrdersContentsController() {}

    public OrderContent createOrderContent(EntityManager em, Ordering ordering, Product product, int quantity, boolean sync) {
        OrderContent orderContent = new OrderContent(ordering, product, quantity);
        product.getOrderContents().add(orderContent);
        App.getInstance().getProductsController().updateQuantity(em, product, product.getAvailableQuantity() - quantity);

        if (sync) {
            DAO.save(em, orderContent);
        } else {
            DAO.persist(em ,orderContent);
        }

        if (em.contains(orderContent)) {
            return orderContent;
        } else {
            return null;
        }
    }

    public void deleteOrderContent(EntityManager em, OrderContent orderContent) {
        DAO.remove(em, orderContent);
    }

    public List<OrderContent> getAll(EntityManager em) {
        return DAO.getAll(em);
    }
}
