package fr.jdbc.controllers;

import fr.jdbc.database.SupplierDAO;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Supplier;
import fr.jdbc.views.SupplierView;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SuppliersController {

    @Getter
    private ArrayList<Supplier> suppliers = new ArrayList<>();
    private SupplierDAO DAO = new SupplierDAO();
    private SupplierView view = new SupplierView();

    public SuppliersController() {

    }

    public Supplier initialize(EntityManager em) {
        return view.initialize(em);
    }

    public Supplier createSupplier(EntityManager em, String name, String forename, FullAddress fullAddress, boolean sync) {
        Supplier supplier = new Supplier(name, forename, fullAddress);
        fullAddress.setSupplier(supplier);

        if (sync) {
            DAO.save(em, supplier);
        } else {
            DAO.persist(em, supplier);
        }

        if (em.contains(supplier)) {
            return supplier;
        } else {
            return null;
        }
    }

    public void updateName(EntityManager em, Supplier supplier, String newName) {
        DAO.updateName(em, supplier, newName);
    }

    public void updateForename(EntityManager em, Supplier supplier, String newForename) {
        DAO.updateForename(em, supplier, newForename);
    }

    public void modifySupplier(EntityManager em) {
        view.modifySupplier(em);
    }

    public void deleteSupplier(EntityManager em, Supplier supplier) {
        DAO.remove(em, supplier);
    }

    public void displayAll(EntityManager em) {
        view.displayAllSuppliers(em);
    }

    public List<Supplier> getAll(EntityManager em) {
        return DAO.getAll(em);
    }

    public Supplier find(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.find(em, criterias);
    }

    public Supplier findByName(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.findByName(em, criterias);
    }
}
