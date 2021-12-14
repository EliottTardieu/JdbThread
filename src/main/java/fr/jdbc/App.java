package fr.jdbc;

import fr.jdbc.database.*;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Order;
import fr.jdbc.models.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class App {

    private static App instance;

    @Getter
    private final ClientDAO clientDAO = new ClientDAO();
    @Getter
    private final FullAddressDAO fullAddressDAO = new FullAddressDAO();
    @Getter
    private final OrderDAO orderDAO = new OrderDAO();
    @Getter
    private final ProductDAO productDAO = new ProductDAO();
    @Getter
    private final SupplierDAO supplierDAO = new SupplierDAO();
    @Getter
    private final SupplyDAO supplyDAO = new SupplyDAO();

    private App(){}

    public void createOrder(LinkedList<Product> products, LinkedList<Integer> quantities, int price, String clientName, FullAddress clientAddress) {
        int clientId;
        HashMap<String, Object> criterias = new HashMap<>();
        criterias.put("nom", clientName);
        criterias.put("adresse", clientAddress.getId());
        if(this.clientDAO.find(criterias).exist()) {
            clientId = this.clientDAO.find(criterias).getId();
        } else {
            Client client = new Client();
            client.setName(clientName);
            client.setFullAddress(clientAddress);
            clientId = client.getId();
        }
        Order order = new Order();
        order.setProducts(products);
        order.setQuantityProduct(quantities);
        order.setPrice(price);
        order.setClient(this.clientDAO.findById(clientId));
    }

    public static App getInstance(){
        if(instance == null) return new App();
        return instance;
    }

}
