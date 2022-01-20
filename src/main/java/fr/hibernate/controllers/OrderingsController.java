package fr.hibernate.controllers;

import fr.hibernate.App;
import fr.hibernate.database.OrderingDAO;
import fr.hibernate.models.Client;
import fr.hibernate.models.OrderContent;
import fr.hibernate.models.Ordering;
import fr.hibernate.views.OrderingView;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class OrderingsController extends Controller<Ordering> {

    private final OrderingView view = new OrderingView();
    private final OrderingDAO DAO = new OrderingDAO();

    public OrderingsController() {
        super(Ordering.class);
    }

    public Ordering initialize(EntityManager em) { return view.initialize(em); }

    public Ordering createEmptyOrdering() {
        return new Ordering();
    }

    public Ordering initializeOrdering(EntityManager em, Ordering ordering, float price, Client client, Set<OrderContent> orderContentSet, boolean autoCommit) {
        ordering.setPrice(price);
        ordering.setClient(client);
        ordering.setOrderContents(orderContentSet);
        client.setOrdering(ordering);
        return this.save(em, DAO, ordering, autoCommit);
    }

    public void updatePrice(EntityManager em, Ordering ordering, float newPrice) {
        DAO.updatePrice(em, ordering, newPrice);
    }

    public void deleteOrdering(EntityManager em, Ordering ordering) {
        this.models.remove(ordering);
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
