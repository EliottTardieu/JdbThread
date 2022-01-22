package fr.hibernate.views;

import dnl.utils.text.table.TextTable;
import fr.hibernate.App;
import fr.hibernate.models.FullAddress;
import fr.hibernate.models.Product;
import fr.hibernate.models.Supplier;
import fr.hibernate.models.Supply;
import fr.hibernate.utils.Logger;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SupplyView implements View {

    public SupplyView() {}

    /**
     * Affiche la liste de toutes les fournitures
     */
    @Override
    public void displayAll(EntityManager em) {
        String[] columnsSupply = {"Id", "Nom", "Prénom", "Prix"};
        ArrayList<ArrayList<Object>> dataSupply = new ArrayList<>();

        String[] columnsSupplyContent = {"Id Fourniture", "Nom", "Catégorie", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataSupplyContent = new ArrayList<>();

        for (Supply supply : App.getInstance().getSuppliesController().getAll(em)) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(supply.getId());
            toAdd.add(supply.getSupplier().getName());
            toAdd.add(supply.getSupplier().getForename());
            toAdd.add(supply.getPrice());
            dataSupply.add(toAdd);

            for (Product product : supply.getProducts()) {
                ArrayList<Object> toAddContent = new ArrayList<>();
                toAddContent.add(supply.getId());
                toAddContent.add(product.getName());
                toAddContent.add(product.getCategory());
                toAddContent.add(product.getUnitPrice());
                dataSupplyContent.add(toAddContent);
            }
        }

        Object[][] formalizedDataSupply = dataSupply.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplyTable = new TextTable(columnsSupply, formalizedDataSupply);

        Object[][] formalizedDataSupplyContent = dataSupplyContent.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);
        TextTable supplyContentTable = new TextTable(columnsSupplyContent, formalizedDataSupplyContent);

        System.out.println("Voulez-vous voir la liste des produits associés aux fournitures également ? (Oui/Non)");
        Scanner scanner = new Scanner(System.in);
        String answerSupply = scanner.nextLine();
        if (answerSupply.equalsIgnoreCase("oui")) {
            supplyTable.printTable();
            supplyContentTable.printTable();
        } else if (answerSupply.equalsIgnoreCase("non")) {
            supplyTable.printTable();
        }
        System.out.println("");
    }

    /**
     * Methode pour créer une fourniture initialisée à partir de saisie dans un terminal.
     *
     * @return Fourniture initialisée
     */
    public Supply initialize(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        // Adresse du fournisseur
        System.out.println("Entrez l'adresse du fournisseur: ");
        String supplierAddress = scanner.nextLine();
        System.out.println("Entrez la ville du fournisseur: ");
        String supplierCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", supplierAddress);
        criteriasAdd.put("city", supplierCity);
        FullAddress supplierFullAddress;
        if (App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd) != null) {
            supplierFullAddress = App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd);
            Logger.fine("Supplier address found.");
        } else {
            supplierFullAddress = App.getInstance().getFullAddressesController().createFullAddress(em, supplierAddress, supplierCity, true);
            Logger.warning("Supplier address unknown, added to database.");
        }

        // Nom du fournisseur et fournisseur
        System.out.println("Entrez le nom du fournisseur: ");
        String supplierName = scanner.nextLine();
        System.out.println("Entrez le prénom du fournisseur: ");
        String supplierForename = scanner.nextLine();
        HashMap<String, Object> criteriasSup = new HashMap<>();
        criteriasSup.put("name", supplierName);
        criteriasSup.put("forename", supplierForename);
        criteriasSup.put("address", supplierFullAddress.getId());
        Supplier supplier;
        if (App.getInstance().getSuppliersController().find(em, criteriasSup) != null) {
            supplier = App.getInstance().getSuppliersController().find(em, criteriasSup);
        } else {
            supplier = App.getInstance().getSuppliersController().createSupplier(em, supplierName, supplierForename, supplierFullAddress, true);
        }

        // Produits
        boolean stop = false;
        ArrayList<Product> products = new ArrayList<>();
        System.out.println("Si vous avez fini, entrez \"stop\".");
        while (!stop) {
            App.getInstance().getProductsController().displayAll(em);
            System.out.println("Entrez le nom du produit à ajouter: ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("stop")) {
                stop = true;
            } else {
                HashMap<String, Object> criteriasProd = new HashMap<>();
                criteriasProd.put("name", choice);
                if (App.getInstance().getProductsController().findByName(em, criteriasProd) != null) {
                    Product product = App.getInstance().getProductsController().findByName(em, criteriasProd);
                    products.add(product);
                    if (!supplier.getProducts().contains(product)) supplier.getProducts().add(product);
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        // Prix
        System.out.println("Entrez le prix de la fourniture: ");
        float price = scanner.nextInt();
        scanner.nextLine();

        Logger.fine("Supply Saved in database.");
        Supply supply = App.getInstance().getSuppliesController().createSupply(em, supplier, price, products, true);
        System.out.println("Fourniture validée avec succès.");

        return supply;
    }
}
