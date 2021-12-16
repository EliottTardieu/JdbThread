package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

public class Product extends Model {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String category;
    @Getter @Setter
    private String species;
    @Getter @Setter
    private int unitPrice;
    @Getter @Setter
    private int availableQuantity;

    public Product() {
        super();
    }

    public Product(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        this.setName(string(data.get("nom")));
        this.setCategory(string(data.get("categorie")));
        this.setSpecies(string(data.get("espece")));
        this.setUnitPrice(integer(data.get("prix_unitaire")));
        this.setAvailableQuantity(integer(data.get("quantite_disponible")));
    }

    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getName());
        toAdd.add(this.getCategory());
        toAdd.add(this.getSpecies());
        toAdd.add(this.getUnitPrice());
        toAdd.add(this.getAvailableQuantity());
        data.add(toAdd);
        return data;
    }
}
