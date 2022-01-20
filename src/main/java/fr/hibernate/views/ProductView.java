package fr.hibernate.views;

import dnl.utils.text.table.TextTable;
import fr.hibernate.App;
import fr.hibernate.models.Product;
import fr.hibernate.utils.Logger;

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
        for (Product product : App.getInstance().getProductsController().getAll(em)) {
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

        // Nom du produit
        System.out.println("Entrez le nom du produit : ");
        String productName = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("name", productName);
        if (App.getInstance().getProductsController().findByName(em, criteriasAdd) != null) {
            Logger.fine("Product found.");
            System.out.println("Produit déjà présent dans le magasin");
            return App.getInstance().getProductsController().findByName(em, criteriasAdd);
        }

        // Catégorie du produit
        System.out.println("Entrez la catégorie (Plante/Fleur): ");
        String productCategory = scanner.nextLine();

        // Espèce
        System.out.println("Entrez l'espèce de votre " + productCategory + " : ");
        String productSpecies = scanner.nextLine();

        // Prix unitaire
        System.out.println("Entrez le prix de votre " + productCategory + " : ");
        float productUnitPrice = scanner.nextFloat();
        scanner.nextLine();

        // Quantité disponible
        System.out.println("Entrez la quantité de votre " + productCategory + " que vous aurez en stock : ");
        int productAvailableQuantity = scanner.nextInt();
        scanner.nextLine();

        Product product = App.getInstance().getProductsController().createProduct(em, productName, productCategory, productSpecies, productUnitPrice, productAvailableQuantity, true);
        System.out.println("Produit ajouté au magasin");

        return product;
    }

    public void modifyProduct(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom du produit que vous voulez modifier: ");
        App.getInstance().getProductsController().displayAll(em);
        String modifiedProductName = scanner.nextLine();
        HashMap<String, Object> criteriasModifiedProduct = new HashMap<>();
        criteriasModifiedProduct.put("name", modifiedProductName);
        if (App.getInstance().getProductsController().findByName(em, criteriasModifiedProduct) != null) {
            Product modifiedProduct = App.getInstance().getProductsController().findByName(em, criteriasModifiedProduct);
            System.out.println("\t1) Modifier le nom\n"
                    + "\t2) Modifier le prix unitaire\n"
                    + "\t3) Modifier la quantité en stock");
            String modifyObject = scanner.nextLine();
            switch (modifyObject) {
                case "1":
                    System.out.println("Entrer le nouveau nom: ");
                    String newNameProduct = scanner.nextLine();
                    App.getInstance().getProductsController().updateName(em, modifiedProduct, newNameProduct);
                    System.out.println("Le nom du produit a bien été modifié\n");
                    break;

                case "2":
                    System.out.println("Entrer le nouveau prix: ");
                    String newPriceProduct = scanner.nextLine();
                    App.getInstance().getProductsController().updatePrice(em, modifiedProduct, Float.parseFloat(newPriceProduct));
                    System.out.println("Le prix du produit a bien été modifié\n");
                    break;

                case "3":
                    System.out.println("Entrer la nouvelle quantité en stock: ");
                    String newQuantityProduct = scanner.nextLine();
                    App.getInstance().getProductsController().updateQuantity(em, modifiedProduct, Integer.parseInt(newQuantityProduct));
                    System.out.println("La quantité en stock du produit a bien été modifié\n");
                    break;
            }
        } else {
            Logger.severe("Unable to find such product.");
        }
    }
}
