package fr.hibernate.views;

import dnl.utils.text.table.TextTable;
import fr.hibernate.App;
import fr.hibernate.models.FullAddress;
import fr.hibernate.models.Product;
import fr.hibernate.models.Supplier;
import fr.hibernate.utils.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
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

        for (Supplier supplier : App.getInstance().getSuppliersController().getAll(em)) {
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

        // Adresse du fournisseur
        System.out.println("Entrez l'adresse du fournisseur: ");
        String supplierAddress = scanner.nextLine();
        System.out.println("Entrez la ville du fournisseur: ");
        String supplierCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("address", supplierAddress);
        criteriasAdd.put("city", supplierCity);
        FullAddress fullAddress;
        if (App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd) != null) {
            fullAddress = App.getInstance().getFullAddressesController().findByFullAddress(em, criteriasAdd);
            Logger.fine("Supplier address found.");
        } else {
            fullAddress = App.getInstance().getFullAddressesController().createFullAddress(em, supplierAddress, supplierCity, true);
            Logger.warning("Supplier address unknown, added to database.");
        }

        // Nom et prénom
        System.out.println("Entrez le nom: ");
        String name = scanner.nextLine();
        System.out.println("Entrez le prénom: ");
        String forename = scanner.nextLine();

        // Produits
        boolean stop = false;
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
                ArrayList<Product> products = new ArrayList<>();
                if (App.getInstance().getProductsController().findByName(em, criteriasProd) != null) {
                    Product product = App.getInstance().getProductsController().findByName(em, criteriasProd);
                    products.add(product);
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        return App.getInstance().getSuppliersController().createSupplier(em, name, forename, fullAddress, true);
    }

    public void modifySupplier(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom du fournisseur que vous voulez modifier: ");
        App.getInstance().getSuppliersController().displayAll(em);
        String modifiedSupplierSc = scanner.nextLine();
        HashMap<String, Object> criteriasModifiedSupplier = new HashMap<>();
        criteriasModifiedSupplier.put("name", modifiedSupplierSc);
        if (App.getInstance().getSuppliersController().findByName(em, criteriasModifiedSupplier) != null) {
            Supplier modifiedSupplier = App.getInstance().getSuppliersController().findByName(em, criteriasModifiedSupplier);
            System.out.println("\t1) Modifier le nom\n"
                    + "\t2) Modifier le prénom");
            String modifySupplier = scanner.nextLine();
            switch (modifySupplier) {
                case "1":
                    System.out.println("Entrez le nouveau Nom du fournisseur: ");
                    App.getInstance().getSuppliersController().updateName(em, modifiedSupplier, scanner.nextLine());
                    break;
                case "2":
                    System.out.println("Entrez le nouveau Prénom du fournisseur: ");
                    App.getInstance().getSuppliersController().updateForename(em, modifiedSupplier, scanner.nextLine());
                    break;
            }
        } else {
            Logger.severe("Unable to find such supplier.");
        }
    }
}
