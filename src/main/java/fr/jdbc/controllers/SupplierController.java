package fr.jdbc.controllers;

import fr.jdbc.models.Model;
import fr.jdbc.models.Supplier;

import java.util.ArrayList;

public class SupplierController extends Controller<Supplier> {

    public SupplierController() {
        super(Supplier.class);
    }

    @Override
    public ArrayList<ArrayList<Object>> display(Supplier object, ArrayList<ArrayList<Object>> data, String... type) {
        return null;
        //TODO Move all methods from models to here
    }
}
