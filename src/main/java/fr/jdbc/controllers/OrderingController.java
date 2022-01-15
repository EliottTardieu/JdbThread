package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.OrderContent;
import fr.jdbc.models.Ordering;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Set;

public class OrderingController {
    public OrderingController() {

    }

    public Ordering createOrdering(EntityManager em, Ordering ordering, float price, Client client, Set<OrderContent> orderContentSet) {
        ordering.setPrice(price);
        ordering.setClient(client);
        ordering.setOrderContents(orderContentSet);
        client.setOrdering(ordering);

        App.getInstance().getOrderingDAO().save(em, ordering);

        if (em.contains(ordering)) {
            return ordering;
        } else {
            return null;
        }
    }

    public void updatePrice(EntityManager em, Ordering ordering, float newPrice) {
        App.getInstance().getOrderingDAO().updatePrice(em, ordering, newPrice);
    }

    public void deleteOrdering(EntityManager em, Ordering ordering) {
        App.getInstance().getOrderingDAO().remove(em, ordering);
    }

    public float computePrice(Set<OrderContent> orderContents, Client client) {
        float price = 0;
        for (OrderContent orderContent : orderContents) {
            price += orderContent.getProduct().getUnitPrice() * orderContent.getQuantity();
        }
        price = price * App.TVA - client.getDiscount();
        return price;
    }
}
