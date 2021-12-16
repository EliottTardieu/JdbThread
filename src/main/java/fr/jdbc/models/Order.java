package fr.jdbc.models;

import fr.jdbc.App;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
        this.products = new LinkedList<>();
        this.quantityProduct = new LinkedList<>();
        this.hydrate(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        for (String id : string(data.get("id_produits")).split(",")) {
            this.products.addLast(App.getInstance().getProductDAO().findById(Integer.parseInt(id)));
        }
        for (String quantity : string(data.get("quantite_produits")).split(",")) {
            this.quantityProduct.addLast(Integer.parseInt(quantity));
        }
        this.setClient(App.getInstance().getClientDAO().findById(integer(data.get("id_client"))));
        this.setPrice(floatNumber(data.get("price")));
    }


    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getPrice());
        toAdd.add(this.getClient().getName());
        toAdd.add(this.getClient().getAddress().getAddress());
        toAdd.add(this.getClient().getAddress().getCity());
        data.add(toAdd);
        return data;
    }

    public ArrayList<ArrayList<Object>> displayContent(ArrayList<ArrayList<Object>> content, int numProduct) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getProducts().get(numProduct).getName());
        toAdd.add(this.getQuantityProduct().get(numProduct));
        content.add(toAdd);
        return content;
    }
}
