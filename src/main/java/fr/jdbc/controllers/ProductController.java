package fr.jdbc.controllers;

import fr.jdbc.App;
import fr.jdbc.database.ProductDAO;
import fr.jdbc.models.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class ProductController {
    public Product createProduct(EntityManager em, String name, String category, String species, float unitPrice, int availableQuantity) {
        Product product = new Product(name, category, species, unitPrice, availableQuantity);

        App.getInstance().getProductDAO().save(em, product);

        if (em.contains(product)) {
            return product;
        } else {
            return null;
        }
    }

    public void updateName(EntityManager em, Product product, String newName) {
        App.getInstance().getProductDAO().updateProductName(em, product, newName);
    }

    public void updatePrice(EntityManager em, Product product, float newPrice) {
        App.getInstance().getProductDAO().updateProductPrice(em, product, newPrice);
    }

    public void deleteProduct(EntityManager em, Product product) {
        App.getInstance().getProductDAO().remove(em, product);
    }
}
