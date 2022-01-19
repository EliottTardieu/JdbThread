package fr.jdbc;

import fr.jdbc.controllers.*;
import lombok.Getter;

public class App {

    public static final float TVA = 1.15f;
    private static App instance;

    @Getter
    private final ClientsController clientsController = new ClientsController();
    @Getter
    private final FullAddressesController fullAddressesController = new FullAddressesController();
    @Getter
    private final OrdersContentsController ordersContentsController = new OrdersContentsController();
    @Getter
    private final OrderingsController orderingsController = new OrderingsController();
    @Getter
    private final ProductsController productsController = new ProductsController();
    @Getter
    private final SuppliersController suppliersController = new SuppliersController();
    @Getter
    private final SuppliesController suppliesController = new SuppliesController();

    private App() {
    }

    public static App getInstance() {
        if (instance == null) return new App();
        return instance;
    }
}
