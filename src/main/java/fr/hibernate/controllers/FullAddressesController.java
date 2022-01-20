package fr.hibernate.controllers;

import fr.hibernate.database.FullAddressDAO;
import fr.hibernate.models.FullAddress;

import javax.persistence.EntityManager;
import java.util.HashMap;

public class FullAddressesController extends Controller<FullAddress> {

    private final FullAddressDAO DAO = new FullAddressDAO();

    public FullAddressesController() {
        super(FullAddress.class);
    }

    public FullAddress createFullAddress(EntityManager em, String address, String city, boolean autoCommit) {
        FullAddress fullAddress = new FullAddress(address, city);
        return this.save(em, DAO, fullAddress, autoCommit);
    }

    public void updateAddress(EntityManager em, FullAddress fullAddress, String newAddress) {
        DAO.updateAddress(em, fullAddress, newAddress);
    }

    public void deleteFullAddress(EntityManager em, FullAddress fullAddress) {
        this.models.remove(fullAddress);
        DAO.remove(em, fullAddress);
    }

    public FullAddress findByFullAddress(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.findByFullAddress(em, criterias);
    }
}
