package fr.jdbc.models;

import fr.jdbc.App;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Supplier extends Model {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String forename;
    @Getter @Setter
    private ArrayList<Product> products = new ArrayList<>();
    @Getter @Setter
    private FullAddress address;

    public Supplier() {
        super();
    }

    public Supplier(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        this.setName(string(data.get("nom")));
        this.setForename(string(data.get("prenom")));
        for (String id : string(data.get("id_produits")).split(",")) {
            this.products.add(App.getInstance().getProductDAO().findById(Integer.parseInt(id)));
        }
        this.setAddress(App.getInstance().getFullAddressDAO().findById(integer(data.get("adresse"))));
    }
}