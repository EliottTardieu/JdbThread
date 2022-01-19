package fr.jdbc.controllers;

import fr.jdbc.database.FullAddressDAO;
import fr.jdbc.models.FullAddress;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;

public class FullAddressesController {

    @Getter
    private final ArrayList<FullAddress> fullAddresses = new ArrayList<>();
    private final FullAddressDAO DAO = new FullAddressDAO();

    public FullAddressesController() {}

    public FullAddress createFullAddress(EntityManager em, String address, String city, boolean autoCommit) {
        FullAddress fullAddress = new FullAddress(address, city);

        if (autoCommit) {
            DAO.save(em, fullAddress);
        } else {
            DAO.persist(em, fullAddress);
        }

        if (em.contains(fullAddress)) {
            return fullAddress;
        } else {
            return null;
        }
    }

    public void updateAddress(EntityManager em, FullAddress fullAddress, String newAddress) {
        DAO.updateAddress(em, fullAddress, newAddress);
    }

    public void deleteFullAddress(EntityManager em, FullAddress fullAddress) {
        DAO.remove(em, fullAddress);
    }

    public FullAddress findByFullAddress(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.findByFullAddress(em, criterias);
    }
}
