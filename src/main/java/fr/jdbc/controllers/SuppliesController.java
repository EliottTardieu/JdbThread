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
        view.displayAllSupplies(em);
    }

    public List<Supply> getAll(EntityManager em) {
        return DAO.getAll(em);
    }
}
