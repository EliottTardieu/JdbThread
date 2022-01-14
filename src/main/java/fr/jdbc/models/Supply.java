package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Supply {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL)
    private List<Product> products;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    @Getter @Setter
    private float price;

    public Supply() {
        this.products = new ArrayList<>();
    }

    public Supply(Supplier supplier, float price) {
        this.products = new ArrayList<>();
        this.supplier = supplier;
        this.price = price;
    }

    /**
     * Methode pour créer une fourniture initialisée à partir de saisie dans un terminal.
     *
     * @return Fourniture initialisée
     */
    /*
    public Supply initialize() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        String supplierName;
        String supplierForename;
        String supplierAddress;
        String supplierCity;
        FullAddress supplierFullAddress;

        // Adresse du fournisseur
        System.out.println("Entrez l'adresse du fournisseur: ");
        supplierAddress = scanner.nextLine();
        System.out.println("Entrez la ville du fournisseur: ");
        supplierCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("adresse", supplierAddress);
        criteriasAdd.put("ville", supplierCity);
        if (App.getInstance().getFullAddressDAO().find(criteriasAdd) != null) {
            supplierFullAddress = App.getInstance().getFullAddressDAO().find(criteriasAdd);
            Logger.fine("Supplier address found.");
        } else {
            supplierFullAddress = new FullAddress();
            supplierFullAddress.setAddress(supplierAddress);
            supplierFullAddress.setCity(supplierCity);
            App.getInstance().getFullAddressDAO().save(supplierFullAddress);
            Logger.warning("Supplier address unknown, added to database.");
        }

        // Nom du fournisseur et fournisseur
        System.out.println("Entrez le nom du fournisseur: ");
        supplierName = scanner.nextLine();
        System.out.println("Entrez le prénom du fournisseur: ");
        supplierForename = scanner.nextLine();
        HashMap<String, Object> criteriasSup = new HashMap<>();
        criteriasSup.put("nom", supplierName);
        criteriasSup.put("prenom", supplierForename);
        if (App.getInstance().getSupplierDAO().find(criteriasSup) != null) {
            this.supplier = App.getInstance().getSupplierDAO().find(criteriasSup);
        } else {
            this.supplier = new Supplier();
            this.supplier.setName(supplierName);
            this.supplier.setForename(supplierForename);
            this.supplier.setAddress(supplierFullAddress);
            App.getInstance().getSupplierDAO().save(supplier);
        }

        // Produits
        boolean stop = false;
        System.out.println("Si vous avez fini, entrez \"stop\".");
        while (!stop) {
            App.getInstance().displayAllProducts();
            System.out.println("Entrez le nom du produit à ajouter: ");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("stop")) {
                stop = true;
            } else {
                HashMap<String, Object> criteriasProd = new HashMap<>();
                criteriasProd.put("nom", choice);
                if (App.getInstance().getProductDAO().find(criteriasProd) != null) {
                    Product product = App.getInstance().getProductDAO().find(criteriasProd);
                    this.products.add(product);
                    if (!supplier.getProducts().contains(product)) supplier.getProducts().add(product);
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        // Prix
        System.out.println("Entrez le prix de la fourniture: ");
        this.price = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Fourniture terminée, confirmer l'enregistrement ? ");
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("oui") || choice.equals("yes")) {
            Logger.fine("Supply Saved in database.");
            this.save();
            System.out.println("Fourniture validée avec succès.");
        } else if (choice.equalsIgnoreCase("non") || choice.equals("no")) {
            Logger.warning("Supply not saved, the content has been reset.");
            this.reset();
            System.out.println("Fourniture annulée avec succès.");
        } else {
            Logger.severe("Unknown answer, supply was reset by default.");
            this.reset();
        }
        return this;
    }

    private void save() {
        App.getInstance().getSupplyDAO().save(this);
    }

    private void reset() {
        // Pour remettre les quantités disponibles si la commande est annulée.
        this.products = new ArrayList<>();
        this.price = 0;
        this.supplier = null;
    }
    */
}
