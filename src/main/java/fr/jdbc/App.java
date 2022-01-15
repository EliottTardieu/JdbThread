package fr.jdbc;

import dnl.utils.text.table.TextTable;
import fr.jdbc.controllers.*;
import fr.jdbc.database.*;
import fr.jdbc.models.*;
import fr.jdbc.utils.Logger;
import fr.jdbc.views.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class App {

    public static final float TVA = 1.15f;
    private static App instance;

    @Getter
    private final ClientDAO clientDAO = new ClientDAO();
    @Getter
    private final FullAddressDAO fullAddressDAO = new FullAddressDAO();
    @Getter
    private final OrderContentDAO orderContentDAO = new OrderContentDAO();
    @Getter
    private final OrderingDAO orderingDAO = new OrderingDAO();
    @Getter
    private final ProductDAO productDAO = new ProductDAO();
    @Getter
    private final SupplierDAO supplierDAO = new SupplierDAO();
    @Getter
    private final SupplyDAO supplyDAO = new SupplyDAO();

    @Getter
    private final ClientView clientView = new ClientView();
    @Getter
    private final FullAddressView fullAddressView = new FullAddressView();
    @Getter
    private final OrderContentView orderContentView = new OrderContentView();
    @Getter
    private final OrderingView orderingView = new OrderingView();
    @Getter
    private final ProductView productView = new ProductView();
    @Getter
    private final SupplierView supplierView = new SupplierView();
    @Getter
    private final SupplyView supplyView = new SupplyView();

    @Getter
    private final ClientController clientController = new ClientController();
    @Getter
    private final FullAddressController fullAddressController = new FullAddressController();
    @Getter
    private final OrderContentController orderContentController = new OrderContentController();
    @Getter
    private final OrderingController orderingController = new OrderingController();
    @Getter
    private final ProductController productController = new ProductController();
    @Getter
    private final SupplierController supplierController = new SupplierController();
    @Getter
    private final SupplyController supplyController = new SupplyController();

    private App() {
    }

    public static App getInstance() {
        if (instance == null) return new App();
        return instance;
    }
}
