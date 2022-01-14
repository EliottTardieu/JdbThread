package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.SupplyDAO;
import fr.jdbc.models.Supplier;
import fr.jdbc.models.Supply;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class SupplyController {
    public Supply createSupply(EntityManager em, Supplier supplier, float price) {
        Supply supply = new Supply(supplier, price);
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
