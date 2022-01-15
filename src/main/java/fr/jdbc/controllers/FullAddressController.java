package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.FullAddressDAO;
import fr.jdbc.models.FullAddress;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class FullAddressController {
    public FullAddressController() {

    }

    public FullAddress createFullAddress(EntityManager em, String address, String city) {
        FullAddress fullAddress = new FullAddress(address, city);

        App.getInstance().getFullAddressDAO().save(em, fullAddress);

        if (em.contains(fullAddress)) {
            return fullAddress;
        } else {
            return null;
        }
    }

    public void updateAddress(EntityManager em, FullAddress fullAddress, String newAddress) {
        App.getInstance().getFullAddressDAO().updateAddress(em, fullAddress, newAddress);
    }

    public void deleteFullAddress(EntityManager em, FullAddress fullAddress) {
        App.getInstance().getFullAddressDAO().remove(em, fullAddress);
    }
}
