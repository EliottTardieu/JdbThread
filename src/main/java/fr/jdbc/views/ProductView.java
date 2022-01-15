package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public class ProductView {
    public ProductView() {

    }
    /**
     * Affiche la liste de tous les produits
     */
    public void displayAllProducts(EntityManager em) {
        String[] columnsProducts = {"Id", "Nom", "Catégorie", "Espèce", "Prix Unitaire (HT)", "Stock Disponible"};
        ArrayList<ArrayList<Object>> dataProducts = new ArrayList<>();
        for (Product product : App.getInstance().getProductDAO().getAll(em)) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(product.getId());
            toAdd.add(product.getName());
            toAdd.add(product.getCategory());
            toAdd.add(product.getSpecies());
            toAdd.add(product.getUnitPrice());
            toAdd.add(product.getAvailableQuantity());
            dataProducts.add(toAdd);
        }
        Object[][] formalizedDataProducts = dataProducts.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable productsTable = new TextTable(columnsProducts, formalizedDataProducts);
        productsTable.printTable();
    }
}
