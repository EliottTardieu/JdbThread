package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.Ordering;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class OrderingController {
    public Ordering createOrdering(EntityManager em, float price, Client client) {
        Ordering ordering = new Ordering(price, client);
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
}
