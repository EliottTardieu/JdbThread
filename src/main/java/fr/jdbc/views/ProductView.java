package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Product;
import fr.jdbc.utils.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    /**
     * Methode pour créer une produit initialisée à partir de saisie dans un terminal.
     *
     * @return Commande initialisée
     */
    public Product initialize(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        String productName;
        String productCategory;
        String productSpecies;
        float productUnitPrice;
        int productAvailableQuantity;

        // Nom du produit
        System.out.println("Entrez le nom du produit : ");
        productName = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("name", productName);
        if (App.getInstance().getProductDAO().findByName(em, criteriasAdd) != null) {
            Logger.fine("Product found.");
            System.out.println("Produit déjà présent dans le magasin");
            return App.getInstance().getProductDAO().findByName(em, criteriasAdd);
        }

        // Catégorie du produit
        System.out.println("Entrez la catégorie (Plante/Fleur): ");
        productCategory = scanner.nextLine();

        // Espèce
        System.out.println("Entrez l'espèce de votre " + productCategory + " : ");
        productSpecies = scanner.nextLine();

        // Prix unitaire
        System.out.println("Entrez le prix de votre " + productCategory + " : ");
        productUnitPrice = scanner.nextFloat();
        scanner.nextLine();

        // Quantité disponible
        System.out.println("Entrez la quantité de votre " + productCategory + " que vous aurez en stock : ");
        productAvailableQuantity = scanner.nextInt();
        scanner.nextLine();

        Product product = App.getInstance().getProductController().createProduct(em, productName, productCategory, productSpecies, productUnitPrice, productAvailableQuantity);
        System.out.println("Produit ajouté au magasin");

        return product;
    }
}
