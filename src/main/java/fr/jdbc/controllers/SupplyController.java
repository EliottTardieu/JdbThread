package fr.jdbc.controllers;

import fr.jdbc.models.Supply;

import java.util.ArrayList;

public class SupplyController extends Controller<Supply> {

    public SupplyController() {
        super(Supply.class);
    }

    @Override
    public ArrayList<ArrayList<Object>> display(Supply object, ArrayList data, String... type) {
        return null;
    }

}
