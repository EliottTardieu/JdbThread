package fr.jdbc.models;

import fr.jdbc.App;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;

public class Order extends Model {

    @Getter @Setter
    private LinkedList<Product> products = new LinkedList<>();
    @Getter @Setter
    private LinkedList<Integer> quantityProduct = new LinkedList<>();
    @Getter @Setter
    private float price;
    @Getter @Setter
    private Client client;

    public Order() {
        super();
    }

    public Order(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        for (String id : string(data.get("id_produits")).split(",")) {
            this.products.add(App.getInstance().getProductDAO().findById(Integer.parseInt(id)));
        }
        for (String quantity : string(data.get("quantite_produits")).split(",")) {
            this.quantityProduct.add(Integer.parseInt(quantity));
        }
        this.setClient(App.getInstance().getClientDAO().findById(integer(data.get("id"))));
        this.setPrice(floatNumber(data.get("price")));
    }
}
