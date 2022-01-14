package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Product {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String category;

    @Getter @Setter
    private String species;

    @Getter @Setter
    private float unitPrice;

    @Getter @Setter
    private int availableQuantity;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Supply supply;

    @Getter @Setter
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderContent> orderContents;

    public Product() {
        this.orderContents = new HashSet<>();
    }

    public Product(String name, String category, String species, float unitPrice, int availableQuantity) {
        this.orderContents = new HashSet<>();
        this.name = name;
        this.category = category;
        this.species = species;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
    }

    /**
     * Methode pour créer une produit initialisée à partir de saisie dans un terminal.
     *
     * @return Commande initialisée
     */
    /*
    public Product initialize() {
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
        criteriasAdd.put("nom", productName);
        if (App.getInstance().getProductDAO().find(criteriasAdd) != null) {
            Logger.fine("Product found.");
            System.out.println("Produit déjà présent dans le magasin");
            return App.getInstance().getProductDAO().find(criteriasAdd);
        } else {
            this.setName(productName);
        }

        // Catégorie du produit
        System.out.println("Entrez la catégorie (Plante/Fleur): ");
        productCategory = scanner.nextLine();
        this.setCategory(productCategory);

        // Espèce
        System.out.println("Entrez l'espèce de votre " + productCategory + " : ");
        productSpecies = scanner.nextLine();
        this.setSpecies(productSpecies);

        // Prix unitaire
        System.out.println("Entrez le prix de votre " + productCategory + " : ");
        productUnitPrice = scanner.nextFloat();
        scanner.nextLine();
        this.setUnitPrice(productUnitPrice);

        // Quantité disponible
        System.out.println("Entrez la quantité de votre " + productCategory + " que vous aurez en stock : ");
        productAvailableQuantity = scanner.nextInt();
        scanner.nextLine();
        this.setAvailableQuantity(productAvailableQuantity);

        App.getInstance().getProductDAO().save(this);
        System.out.println("Produit ajouté au magasin");

        return this;
    }
    */
    /**
     * Stocke les informations d'un produit dans une liste, qui est elle-même mise
     * dans la liste de tous les produits.
     *
     * @param data La liste de tous les produits que l'on met à jour à chaque appel.
     * @return La liste des produits mise à jour.
     */
    /*
    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getName());
        toAdd.add(this.getCategory());
        toAdd.add(this.getSpecies());
        toAdd.add(this.getUnitPrice());
        toAdd.add(this.getAvailableQuantity());
        data.add(toAdd);
        return data;
    }
    */
}
