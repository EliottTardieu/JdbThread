package fr.jdbc;

import fr.jdbc.database.*;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Order;
import fr.jdbc.models.Product;
import fr.jdbc.utils.Logger;
import lombok.Getter;

import java.util.*;

public class App {

    private static final float TVA = 1.15f;
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

    public void createOrder(LinkedList<Product> products, LinkedList<Integer> quantities, String clientName, String clientAddress, String clientCity) {
        if (products.size() != quantities.size()) {
            Logger.severe("Unable to handle product with no quantity.");
        } else {
            //TODO Pour chaque produit remove en DB la qt associée.
            Client client;
            FullAddress clientFullAddress;
            float price = 0;

            HashMap<String, Object> criteriasAdd = new HashMap<>();
            criteriasAdd.put("adresse", clientAddress);
            criteriasAdd.put("ville", clientCity);

            if (this.fullAddressDAO.find(criteriasAdd).exist()) {
                clientFullAddress = this.fullAddressDAO.find(criteriasAdd);
            } else {
                clientFullAddress = new FullAddress();
                clientFullAddress.setAddress(clientAddress);
                clientFullAddress.setCity(clientCity);
                this.fullAddressDAO.save(clientFullAddress);
            }

            HashMap<String, Object> criteriasCli = new HashMap<>();
            criteriasCli.put("nom", clientName);
            criteriasCli.put("adresse", clientFullAddress.getId());

            if (this.clientDAO.find(criteriasCli).exist()) {
                client = this.clientDAO.find(criteriasCli);
            } else {
                client = new Client();
                client.setName(clientName);
                client.setAddress(clientFullAddress);
                this.clientDAO.save(client);
            }

            for (int i = 0; i < products.size(); i++) {
                price += products.get(i).getUnitPrice() * quantities.get(i);
            }
            price = price*App.TVA-client.getDiscount();

            Order order = new Order();
            order.setProducts(products);
            order.setQuantityProduct(quantities);
            order.setPrice(price);
            order.setClient(client);
            this.orderDAO.save(order);
        }
    }

    public static App getInstance(){
        if(instance == null) return new App();
        return instance;
    }

    public List<Object> twoDArrayToList(Object[][] array) {
        List<Object> list = new ArrayList<>();
        for (Object obj : array) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

}
