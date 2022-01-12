package fr.jdbc.controllers;

import fr.jdbc.models.Product;

import java.util.ArrayList;

public class ProductController extends Controller<Product> {

    public ProductController() {
        super(Product.class);
    }

    @Override
    public ArrayList<ArrayList<Object>> display(Product object, ArrayList<ArrayList<Object>> data, String... type) {
        return null;
    }
}
