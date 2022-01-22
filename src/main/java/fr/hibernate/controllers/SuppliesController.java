package fr.hibernate.controllers;

import fr.hibernate.database.SupplyDAO;
import fr.hibernate.models.Product;
import fr.hibernate.models.Supplier;
import fr.hibernate.models.Supply;
import fr.hibernate.views.SupplyView;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class SuppliesController extends Controller<Supply> {

    private final SupplyDAO DAO = new SupplyDAO();
    private final SupplyView view = new SupplyView();

    public SuppliesController() {
        super(Supply.class);
    }

    public Supply initialize(EntityManager em) {
        return view.initialize(em);
    }

    public Supply createSupply(EntityManager em, Supplier supplier, float price, ArrayList<Product> products, boolean autoCommit) {
        Supply supply = new Supply(supplier, price, products);
        supplier.setSupply(supply);
        return this.save(em, DAO, supply, autoCommit);
    }

    public void deleteSupply(EntityManager em, Supply supply) {
        this.models.remove(supply);
        DAO.remove(em, supply);
    }

    public void displayAll(EntityManager em) {
        view.displayAll(em);
    }

    public List<Supply> getAll(EntityManager em) {
        return DAO.getAll(em);
    }
}
