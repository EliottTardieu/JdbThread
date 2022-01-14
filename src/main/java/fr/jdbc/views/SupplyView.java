package fr.jdbc.views;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import fr.jdbc.models.Product;
import fr.jdbc.models.Supply;

import java.util.ArrayList;
import java.util.Scanner;

public class SupplyView {
    public SupplyView() {

    }

    /**
     * Affiche la liste de toutes les fournitures
     */
    public void displayAllSupplies() {
        String[] columnsSupply = {"Id", "Nom", "Prénom", "Prix"};
        ArrayList<ArrayList<Object>> dataSupply = new ArrayList<>();

        String[] columnsSupplyContent = {"Id Fourniture", "Nom", "Catégorie", "Prix Unitaire"};
        ArrayList<ArrayList<Object>> dataSupplyContent = new ArrayList<>();

        for (Supply supply : App.getInstance().getSupplyDAO().getAll(App.getInstance().getEm())) {
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
}
