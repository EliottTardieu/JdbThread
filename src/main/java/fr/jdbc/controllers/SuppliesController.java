package fr.jdbc.controllers;

import fr.jdbc.database.SupplyDAO;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;
import fr.jdbc.models.Supply;
import fr.jdbc.views.SupplyView;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class SuppliesController {

    @Getter
    private ArrayList<Supply> supplies = new ArrayList<>();
    private SupplyDAO DAO = new SupplyDAO();
    private SupplyView view = new SupplyView();

    public SuppliesController() {

    }

    public Supply initialize(EntityManager em) {
        return view.initialize(em);
    }

    public Supply createSupply(EntityManager em, Supplier supplier, float price, ArrayList<Product> products, boolean sync) {
        Supply supply = new Supply(supplier, price, products);
        supplier.setSupply(supply);

        if (sync) {
            DAO.save(em, supply);
        } else {
            DAO.persist(em, supply);
        }

        if (em.contains(supply)) {
            return supply;
        } else {
            return null;
        }
    }

    public void deleteSupply(EntityManager em, Supply supply) {
        DAO.remove(em, supply);
    }

    public void displayAll(EntityManager em) {
        view.displayAllSupplies(em);
    }

    public List<Supply> getAll(EntityManager em) {
        return DAO.getAll(em);
    }
}
