package fr.hibernate.controllers;

import fr.hibernate.database.SupplierDAO;
import fr.hibernate.models.FullAddress;
import fr.hibernate.models.Supplier;
import fr.hibernate.views.SupplierView;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;

public class SuppliersController extends Controller<Supplier> {

    private final SupplierDAO DAO = new SupplierDAO();
    private final SupplierView view = new SupplierView();

    public SuppliersController() {
        super(Supplier.class);
    }

    public Supplier initialize(EntityManager em) {
        return view.initialize(em);
    }

    public Supplier createSupplier(EntityManager em, String name, String forename, FullAddress fullAddress, boolean autoCommit) {
        Supplier supplier = new Supplier(name, forename, fullAddress);
        fullAddress.setSupplier(supplier);
        return this.save(em, DAO, supplier, autoCommit);
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
        this.models.remove(supplier);
        DAO.remove(em, supplier);
    }

    public void displayAll(EntityManager em) {
        view.displayAll(em);
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
