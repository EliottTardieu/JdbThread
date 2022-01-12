package fr.jdbc.controllers;

import fr.jdbc.models.Client;

import java.util.ArrayList;

public class ClientController extends Controller<Client> {

    public ClientController(Class<Client> type) {
        super(type);
    }

    @Override
    public ArrayList<ArrayList<Object>> display(Client object, ArrayList<ArrayList<Object>> data, String... type) {
        return null;
    }
}
