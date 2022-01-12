package fr.jdbc.controllers;

import fr.jdbc.models.Order;

import java.util.ArrayList;

public class OrderController extends Controller<Order> {

    public OrderController(Class<Order> type) {
        super(type);
    }

    @Override
    public ArrayList<ArrayList<Object>> display(Order object, ArrayList<ArrayList<Object>> data, String... type) {
        return null;
    }
}
