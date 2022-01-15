package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.SupplyDAO;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;
import fr.jdbc.models.Supply;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class SupplyController {
    public SupplyController() {

    }

    public Supply createSupply(EntityManager em, Supplier supplier, float price, ArrayList<Product> products) {
        Supply supply = new Supply(supplier, price, products);
        supplier.setSupply(supply);

        App.getInstance().getSupplyDAO().save(em, supply);

        if (em.contains(supply)) {
            return supply;
        } else {
            return null;
        }
    }

    public void deleteSupply(EntityManager em, Supply supply) {
        App.getInstance().getSupplyDAO().remove(em, supply);
    }
}
