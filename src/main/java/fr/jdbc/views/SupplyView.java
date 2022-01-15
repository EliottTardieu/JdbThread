package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.FullAddress;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;
import fr.jdbc.models.Supply;
import fr.jdbc.utils.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SupplyView {
    public SupplyView() {

    }

    /**
     * Affiche la liste de toutes les fournitures
     */
    public void displayAllSupplies(EntityManager em) {
        String[] columnsSupply = {"Id", "Nom", "Prénom", "Prix"};
        ArrayList<ArrayList<Object>> dataSupply = new ArrayList<>();

        String[] columnsSupplyContent = {"Id Fourniture", "Nom", "Catégorie", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataSupplyContent = new ArrayList<>();

        for (Supply supply : App.getInstance().getSupplyDAO().getAll(em)) {
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
        String choice;
        Supply supply = null;
        Supplier supplier;
        String supplierName;
        String supplierForename;
        String supplierAddress;
        String supplierCity;
        FullAddress supplierFullAddress;
        ArrayList<Product> products = new ArrayList<>();
        float price;

        // Adresse du fournisseur
        System.out.println("Entrez l'adresse du fournisseur: ");
        supplierAddress = scanner.nextLine();
        System.out.println("Entrez la ville du fournisseur: ");
        supplierCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", supplierAddress);
        criteriasAdd.put("city", supplierCity);
        if (App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd) != null) {
            supplierFullAddress = App.getInstance().getFullAddressDAO().findByFullAddress(em, criteriasAdd);
            Logger.fine("Supplier address found.");
        } else {
            supplierFullAddress = App.getInstance().getFullAddressController().createFullAddress(em, supplierAddress, supplierCity);
            Logger.warning("Supplier address unknown, added to database.");
        }

        // Nom du fournisseur et fournisseur
        System.out.println("Entrez le nom du fournisseur: ");
        supplierName = scanner.nextLine();
        System.out.println("Entrez le prénom du fournisseur: ");
        supplierForename = scanner.nextLine();
        HashMap<String, Object> criteriasSup = new HashMap<>();
        criteriasSup.put("name", supplierName);
        criteriasSup.put("forename", supplierForename);
        criteriasSup.put("address", supplierFullAddress.getId());
        if (App.getInstance().getSupplierDAO().find(em, criteriasSup) != null) {
            supplier = App.getInstance().getSupplierDAO().find(em, criteriasSup);
        } else {
            supplier = App.getInstance().getSupplierController().createSupplier(em, supplierName, supplierForename, supplierFullAddress);
        }

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
                    if (!supplier.getProducts().contains(product)) supplier.getProducts().add(product);
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        // Prix
        System.out.println("Entrez le prix de la fourniture: ");
        price = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Fourniture terminée, confirmer l'enregistrement ? ");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("oui") || choice.equals("yes")) {
            Logger.fine("Supply Saved in database.");
            supply = App.getInstance().getSupplyController().createSupply(em, supplier, price, products);
            System.out.println("Fourniture validée avec succès.");
        } else if (choice.equalsIgnoreCase("non") || choice.equals("no")) {
            Logger.warning("Supply not saved, the content has been reset.");
            // TODO: Supprimer les infos concernant la supply
            System.out.println("Fourniture annulée avec succès.");
        } else {
            Logger.severe("Unknown answer, supply was reset by default.");
            // TODO: Supprimer les infos concernant la supply
        }
        return supply;
    }
}
