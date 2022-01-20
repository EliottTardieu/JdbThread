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
