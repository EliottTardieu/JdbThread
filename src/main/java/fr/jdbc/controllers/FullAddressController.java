package fr.jdbc.controllers;

import fr.jdbc.models.FullAddress;

import java.util.ArrayList;

public class FullAddressController extends Controller<FullAddress> {

    public FullAddressController() {
        super(FullAddress.class);
    }

    @Override
    public ArrayList<ArrayList<Object>> display(FullAddress object, ArrayList<ArrayList<Object>> data, String... type) {
        return null;
    }
}
