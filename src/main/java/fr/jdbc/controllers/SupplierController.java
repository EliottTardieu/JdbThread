package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.SupplierDAO;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Supplier;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class SupplierController {
    public SupplierController() {

    }

    public Supplier createSupplier(EntityManager em, String name, String forename, FullAddress fullAddress) {
        Supplier supplier = new Supplier(name, forename, fullAddress);
        fullAddress.setSupplier(supplier);

        App.getInstance().getSupplierDAO().save(em, supplier);

        if (em.contains(supplier)) {
            return supplier;
        } else {
            return null;
        }
    }

    public void updateName(EntityManager em, Supplier supplier, String newName) {
        App.getInstance().getSupplierDAO().updateName(em, supplier, newName);
    }

    public void updateForename(EntityManager em, Supplier supplier, String newForename) {
        App.getInstance().getSupplierDAO().updateForename(em, supplier, newForename);
    }

    public void deleteSupplier(EntityManager em, Supplier supplier) {
        App.getInstance().getSupplierDAO().remove(em, supplier);
    }
}
