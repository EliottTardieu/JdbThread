package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
}
