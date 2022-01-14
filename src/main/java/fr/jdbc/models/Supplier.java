package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Supplier {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String forename;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private FullAddress address;

    @Getter @Setter
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products;

    @Getter @Setter
    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Supply supply;

    public Supplier() {
        this.products = new ArrayList<>();
    }

    public Supplier(String name, String forename, FullAddress address) {
        this.products = new ArrayList<>();
        this.name = name;
        this.forename = forename;
        this.address = address;
    }

    /**
     * Methode pour créer un fournisseur initialisé à partir de saisie dans un terminal.
     *
     * @return Fournisseur initialisée
     */
    /*
    public Supplier initialize() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        String supplierAddress;
        String supplierCity;

        // Adresse du fournisseur
        System.out.println("Entrez l'adresse du fournisseur: ");
        supplierAddress = scanner.nextLine();
        System.out.println("Entrez la ville du fournisseur: ");
        supplierCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("adresse", supplierAddress);
        criteriasAdd.put("ville", supplierCity);
        if (App.getInstance().getFullAddressDAO().find(criteriasAdd) != null) {
            this.address = App.getInstance().getFullAddressDAO().find(criteriasAdd);
            Logger.fine("Supplier address found.");
        } else {
            this.address = new FullAddress();
            this.address.setAddress(supplierAddress);
            this.address.setCity(supplierCity);
            App.getInstance().getFullAddressDAO().save(this.address);
            Logger.warning("Supplier address unknown, added to database.");
        }

        // Nom et prénom
        System.out.println("Entrez le nom: ");
        this.name = scanner.nextLine();
        System.out.println("Entrez le prénom: ");
        this.forename = scanner.nextLine();

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
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        App.getInstance().getSupplierDAO().save(this);
        return this;
    }
    */
    /**
     * Stocke les informations d'un fournisseur dans une liste, qui est elle-même mise
     * dans la liste de tous les fournisseurs.
     *
     * @param data La liste de tous les fournisseurs que l'on met à jour à chaque appel.
     * @return La liste des fournisseurs mise à jour.
     */
    /*
    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getName());
        toAdd.add(this.getForename());
        toAdd.add(this.getAddress().getAddress());
        toAdd.add(this.getAddress().getCity());
        toAdd.add(this.getProducts());
        data.add(toAdd);
        return data;
    }
    */
    /**
     * Stocke le contenu d'un fournisseur dans une liste, qui est elle-même mise
     * dans la liste concernant le contenu de tous les fournisseurs.
     *
     * @param content La liste avec le contenu de tous les fournisseurs que l'on met à jour à chaque appel.
     * @return La liste du contenu de tous les fournisseurs mise à jour.
     */
    /*
    public ArrayList<ArrayList<Object>> displayContent(ArrayList<ArrayList<Object>> content) {
        for (Product product : this.getProducts()) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(this.getId());
            toAdd.add(product.getName());
            toAdd.add(product.getCategory());
            toAdd.add(product.getUnitPrice());
            content.add(toAdd);
        }
        return content;
    }
    */
}
