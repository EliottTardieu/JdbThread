package fr.jdbc.controllers;

import fr.jdbc.database.ProductDAO;
import fr.jdbc.models.Product;
import fr.jdbc.views.ProductView;
import lombok.Getter;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsController extends Controller<Product> {

    private final ProductDAO DAO = new ProductDAO();
    private final ProductView view = new ProductView();

    public ProductsController() {
        super(Product.class);
    }

    public Product initialize(EntityManager em) {
        return view.initialize(em);
    }

    public Product createProduct(EntityManager em, String name, String category, String species, float unitPrice, int availableQuantity, boolean autoCommit) {
        Product product = new Product(name, category, species, unitPrice, availableQuantity);
        return this.save(em, DAO, product, autoCommit);
    }

    public void updateName(EntityManager em, Product product, String newName) {
        DAO.updateProductName(em, product, newName);
    }

    public void updatePrice(EntityManager em, Product product, float newPrice) {
        DAO.updateProductPrice(em, product, newPrice);
    }

    public void updateQuantity(EntityManager em, Product product, int newQuantity) {
        DAO.updateQuantity(em, product, newQuantity);
    }

    public void modifyProduct(EntityManager em) {
        view.modifyProduct(em);
    }

    public void deleteProduct(EntityManager em, Product product) {
        this.models.remove(product);
        DAO.remove(em, product);
    }

    public void displayAll(EntityManager em) {
        view.displayAllProducts(em);
    }

    public List<Product> getAll(EntityManager em) {
        return DAO.getAll(em);
    }

    public Product findByName(EntityManager em, HashMap<String, Object> criterias) {
        return DAO.findByName(em, criterias);
    }
}
