package fr.hibernate.controllers;

import fr.hibernate.App;
import fr.hibernate.database.OrderContentDAO;
import fr.hibernate.models.OrderContent;
import fr.hibernate.models.Ordering;
import fr.hibernate.models.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdersContentsController extends Controller<OrderContent> {

    private final OrderContentDAO DAO = new OrderContentDAO();

    public OrdersContentsController() {
        super(OrderContent.class);
    }

    public OrderContent createOrderContent(EntityManager em, Ordering ordering, Product product, int quantity, boolean autoCommit) {
        OrderContent orderContent = new OrderContent(ordering, product, quantity);
        product.getOrderContents().add(orderContent);
        App.getInstance().getProductsController().updateQuantity(em, product, product.getAvailableQuantity() - quantity);
        return this.save(em, DAO, orderContent, autoCommit);
    }

    public void deleteOrderContent(EntityManager em, OrderContent orderContent) {
        this.models.remove(orderContent);
        DAO.remove(em, orderContent);
    }

    public List<OrderContent> getAll(EntityManager em) {
        return DAO.getAll(em);
    }
}
