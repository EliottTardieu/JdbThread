package fr.jdbc.models;

import fr.jdbc.App;
import fr.jdbc.utils.Logger;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Supplier extends Model {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String forename;
    @Getter @Setter
    private ArrayList<Product> products = new ArrayList<>();
    @Getter @Setter
    private FullAddress address;

    public Supplier() {
        super();
    }

    public Supplier(HashMap<String, Object> data) {
        this.products = new ArrayList<>();
        this.hydrate(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        this.setName(string(data.get("nom")));
        this.setForename(string(data.get("prenom")));
        for (String id : string(data.get("id_produits")).split(",")) {
            this.products.add(App.getInstance().getProductDAO().findById(Integer.parseInt(id)));
        }
        this.setAddress(App.getInstance().getFullAddressDAO().findById(integer(data.get("adresse"))));
    }

    /**
     *  Methode pour créer un fournisseur initialisé à partir de saisie dans un terminal.
     * @return Fournisseur initialisée
     */
    public Supplier initialize(){
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
        while(!stop) {
            App.getInstance().displayAllProducts();
            System.out.println("Entrez le nom du produit à ajouter: ");
            choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("stop")) {
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

    /**
     * Stocke les informations d'un fournisseur dans une liste, qui est elle-même mise
     * dans la liste de tous les fournisseurs.
     * @param data La liste de tous les fournisseurs que l'on met à jour à chaque appel.
     * @return La liste des fournisseurs mise à jour.
     */
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

    /**
     * Stocke le contenu d'un fournisseur dans une liste, qui est elle-même mise
     * dans la liste concernant le contenu de tous les fournisseurs.
     * @param content La liste avec le contenu de tous les fournisseurs que l'on met à jour à chaque appel.
     * @return La liste du contenu de tous les fournisseurs mise à jour.
     */
    public ArrayList<ArrayList<Object>> displayContent(ArrayList<ArrayList<Object>> content) {
        for(Product product : this.getProducts()) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(this.getId());
            toAdd.add(product.getName());
            toAdd.add(product.getCategory());
            toAdd.add(product.getUnitPrice());
            content.add(toAdd);
        }
        return content;
    }
}
