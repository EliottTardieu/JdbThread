package fr.jdbc.controllers;

import fr.jdbc.models.Model;

import java.util.ArrayList;

public abstract class Controller<T extends Model> {

    private Class<T> type;

    public Controller(Class<T> type){
        this.type = type;
    }

    public abstract ArrayList<ArrayList<Object>> display(T object, ArrayList<ArrayList<Object>> data, String... type);

}
