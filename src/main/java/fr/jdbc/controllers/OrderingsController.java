package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.OrderingDAO;
import fr.jdbc.models.Client;
import fr.jdbc.models.OrderContent;
import fr.jdbc.models.Ordering;
import fr.jdbc.views.OrderingView;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderingsController {

    @Getter
    private ArrayList<Ordering> orderings = new ArrayList<>();
    private OrderingView view = new OrderingView();
    private OrderingDAO DAO = new OrderingDAO();

    public OrderingsController() {}

    public Ordering initialize(EntityManager em) { return view.initialize(em); }

    public Ordering initializeOrdering(EntityManager em, Ordering ordering, float price, Client client, Set<OrderContent> orderContentSet, boolean sync) {
        ordering.setPrice(price);
        ordering.setClient(client);
        ordering.setOrderContents(orderContentSet);
        client.setOrdering(ordering);

        if (sync) {
            DAO.save(em, ordering);
        } else {
            DAO.persist(em, ordering);
        }

        if (em.contains(ordering)) {
            return ordering;
        } else {
            return null;
        }
    }

    public void updatePrice(EntityManager em, Ordering ordering, float newPrice) {
        DAO.updatePrice(em, ordering, newPrice);
    }

    public void deleteOrdering(EntityManager em, Ordering ordering) {
        DAO.remove(em, ordering);
    }

    public float computePrice(Set<OrderContent> orderContents, Client client) {
        float price = 0;
        for (OrderContent orderContent : orderContents) {
            price += orderContent.getProduct().getUnitPrice() * orderContent.getQuantity();
        }
        price = price * App.TVA - client.getDiscount();
        return price;
    }

    public void displayAll(EntityManager em) {
        view.displayAllOrders(em);
    }

    public List<Ordering> getAll(EntityManager em) {
        return DAO.getAll(em);
    }
}
