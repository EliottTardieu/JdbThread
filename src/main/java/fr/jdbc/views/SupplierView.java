package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Client;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;
import fr.jdbc.utils.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SupplierView {
    public SupplierView() {

    }

    /**
     * Affiche la liste de tous les fournisseurs
     */
    public void displayAllSuppliers(EntityManager em) {
        String[] columnsSupplier = {"Id", "Nom", "Prénom", "Adresse", "Ville"};
        ArrayList<ArrayList<Object>> dataSupplier = new ArrayList<>();

        String[] columnsSupplierContent = {"Id Fournisseur", "Nom", "Catégorie", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataSupplierContent = new ArrayList<>();

        for (Supplier supplier : App.getInstance().getSupplierDAO().getAll(em)) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(supplier.getId());
            toAdd.add(supplier.getName());
            toAdd.add(supplier.getForename());
            toAdd.add(supplier.getAddress().getAddress());
            toAdd.add(supplier.getAddress().getCity());
            dataSupplier.add(toAdd);

            for (Product product : supplier.getProducts()) {
                ArrayList<Object> toAddContent = new ArrayList<>();
                toAddContent.add(supplier.getId());
                toAddContent.add(product.getName());
                toAddContent.add(product.getCategory());
                toAddContent.add(product.getUnitPrice());
                dataSupplierContent.add(toAddContent);
            }
        }

        Object[][] formalizedDataSupplier = dataSupplier.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplierTable = new TextTable(columnsSupplier, formalizedDataSupplier);

        Object[][] formalizedDataSupplierContent = dataSupplierContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplierContentTable = new TextTable(columnsSupplierContent, formalizedDataSupplierContent);

        System.out.println("Voulez-vous voir la liste des produits provenant des fournisseurs également ? (Oui/Non)");
        Scanner scanner = new Scanner(System.in);
        String answerSupplier = scanner.nextLine();
        if (answerSupplier.equalsIgnoreCase("oui")) {
            supplierTable.printTable();
            supplierContentTable.printTable();
        } else if (answerSupplier.equalsIgnoreCase("non")) {
            supplierTable.printTable();
        }
        System.out.println("");
    }

    /**
     * Methode pour créer un fournisseur initialisé à partir de saisie dans un terminal.
     *
     * @return Fournisseur initialisée
     */
    public Supplier initialize(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        String supplierAddress;
        String supplierCity;
        FullAddress fullAddress;
        String name;
        String forename;
        ArrayList<Product> products = new ArrayList<>();

        // Adresse du fournisseur
        System.out.println("Entrez l'adresse du fournisseur: ");
        supplierAddress = scanner.nextLine();
        System.out.println("Entrez la ville du fournisseur: ");
        supplierCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", supplierAddress);
        criteriasAdd.put("city", supplierCity);
        if (App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd) != null) {
            fullAddress = App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd);
            Logger.fine("Supplier address found.");
        } else {
            fullAddress = App.getInstance().getFullAddressController().createFullAddress(em, supplierAddress, supplierCity);
            Logger.warning("Supplier address unknown, added to database.");
        }

        // Nom et prénom
        System.out.println("Entrez le nom: ");
        name = scanner.nextLine();
        System.out.println("Entrez le prénom: ");
        forename = scanner.nextLine();

        // Produits
        boolean stop = false;
        System.out.println("Si vous avez fini, entrez \"stop\".");
        while (!stop) {
            App.getInstance().getProductView().displayAllProducts(em);
            System.out.println("Entrez le nom du produit à ajouter: ");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("stop")) {
                stop = true;
            } else {
                HashMap<String, Object> criteriasProd = new HashMap<>();
                criteriasProd.put("name", choice);
                if (App.getInstance().getProductDAO().findByName(em, criteriasProd) != null) {
                    Product product = App.getInstance().getProductDAO().findByName(em, criteriasProd);
                    products.add(product);
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        return App.getInstance().getSupplierController().createSupplier(em, name, forename, fullAddress);
    }
}
