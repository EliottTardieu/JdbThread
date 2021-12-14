package fr.jdbc;

import fr.jdbc.database.*;
import lombok.Getter;

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

    public static App getInstance(){
        if(instance == null) return new App();
        return instance;
    }

}
