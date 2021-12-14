package fr.jdbc.models;

import fr.jdbc.App;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

public class Supply extends Model {

    @Getter @Setter
    private ArrayList<Product> products = new ArrayList<>();
    @Getter @Setter
    private Supplier supplier;
    @Getter @Setter
    private int price;

    public Supply() {
        super();
    }

    public Supply(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        for (String id : string(data.get("id_produits")).split(",")) {
            this.products.add(App.getInstance().getProductDAO().findById(Integer.parseInt(id)));
        }
        this.setPrice(integer(data.get("prix")));
        this.setSupplier(App.getInstance().getSupplierDAO().findById(integer(data.get("id_fournisseur"))));
    }
}
