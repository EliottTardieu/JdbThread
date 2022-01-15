package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.models.OrderContent;
import fr.jdbc.models.Ordering;
import fr.jdbc.models.Product;

import javax.persistence.EntityManager;

public class OrderContentController {
    public OrderContentController() {

    }

    public OrderContent createOrderContent(EntityManager em, Ordering ordering, Product product, int quantity) {
        OrderContent orderContent = new OrderContent(ordering, product, quantity);
        product.getOrderContents().add(orderContent);
        App.getInstance().getProductDAO().updateQuantity(em, product, product.getAvailableQuantity() - quantity);

        App.getInstance().getOrderContentDAO().save(em, orderContent);

        if (em.contains(orderContent)) {
            return orderContent;
        } else {
            return null;
        }
    }

    public void deleteOrderContent(EntityManager em, OrderContent orderContent) {
        App.getInstance().getOrderContentDAO().remove(em, orderContent);
    }
}
