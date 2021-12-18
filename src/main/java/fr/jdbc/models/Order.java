package fr.jdbc.models;

import fr.jdbc.App;
import fr.jdbc.utils.Logger;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Order extends Model {

    @Getter @Setter
    private LinkedList<Product> products = new LinkedList<>();
    @Getter @Setter
    private LinkedList<Integer> quantityProduct = new LinkedList<>();
    @Getter @Setter
    private float price;
    @Getter @Setter
    private Client client;

    private HashMap<Product, Integer> backupQuantity = new HashMap<>();

    public Order() {
        super();
    }

    public Order(HashMap<String, Object> data) {
        this.products = new LinkedList<>();
        this.quantityProduct = new LinkedList<>();
        this.hydrate(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        for (String id : string(data.get("id_produits")).split(",")) {
            this.products.addLast(App.getInstance().getProductDAO().findById(Integer.parseInt(id)));
        }
        for (String quantity : string(data.get("quantite_produits")).split(",")) {
            this.quantityProduct.addLast(Integer.parseInt(quantity));
        }
        this.setClient(App.getInstance().getClientDAO().findById(integer(data.get("id_client"))));
        this.setPrice(floatNumber(data.get("prix")));
    }

    /**
     *  Methode pour créer une commande initialisée à partir de saisie dans un terminal.
     * @return Commande initialisée
     */
    public Order initialize(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        String clientName;
        String clientForename;
        String clientAddress;
        String clientCity;
        FullAddress clientFullAddress;

        // Adresse du client
        System.out.println("Entrez l'adresse du client: ");
        clientAddress = scanner.nextLine();
        System.out.println("Entrez la ville du client: ");
        clientCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("adresse", clientAddress);
        criteriasAdd.put("ville", clientCity);
        if (App.getInstance().getFullAddressDAO().find(criteriasAdd) != null) {
            clientFullAddress = App.getInstance().getFullAddressDAO().find(criteriasAdd);
            Logger.fine("Client address found.");
        } else {
            clientFullAddress = new FullAddress();
            clientFullAddress.setAddress(clientAddress);
            clientFullAddress.setCity(clientCity);
            App.getInstance().getFullAddressDAO().save(clientFullAddress);
            Logger.warning("Client address unknown, added to database.");
        }

        // Nom du client et client
        System.out.println("Entrez le nom du client: ");
        clientName = scanner.nextLine();
        System.out.println("Entrez le prénom du client: ");
        clientForename = scanner.nextLine();
        HashMap<String, Object> criteriasCli = new HashMap<>();
        criteriasCli.put("nom", clientName);
        criteriasCli.put("prenom", clientForename);
        criteriasCli.put("adresse", clientFullAddress.getId());
        if (App.getInstance().getClientDAO().find(criteriasCli) != null) {
            this.client = App.getInstance().getClientDAO().find(criteriasCli);
        } else {
            this.client = new Client();
            this.client.setName(clientName);
            this.client.setForename(clientForename);
            this.client.setAddress(clientFullAddress);
            App.getInstance().getClientDAO().save(client);
        }

        // Produits et Quantités
        boolean stop = false;
        System.out.println("Si vous avez fini, entrez \"stop\".");
        for(Product product : App.getInstance().getProductDAO().getAll()){
            this.backupQuantity.put(product, product.getAvailableQuantity());
        }
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
                    System.out.println("Entrez une quantité pour ce produit: (stop quand vous avez fini)");
                    int qt = scanner.nextInt();
                    scanner.nextLine();
                    if (qt <= 0) {
                        System.out.println("Quantité invalide, sélectionnez une quantité entre 1 et " + product.getAvailableQuantity());
                    } else if (qt > product.getAvailableQuantity()) {
                        System.out.println("Quantité invalide, vous ne pouvez pas mettre plus de produits que ce que vous avez de disponible.");
                    } else {
                        this.products.addLast(product);
                        this.quantityProduct.addLast(qt);
                        product.setAvailableQuantity(product.getAvailableQuantity() - qt);
                        App.getInstance().getProductDAO().save(product);
                    }
                } else {
                    System.out.println("Wrong product name, please select a valid product.");
                    Logger.warning("Wrong product name in input.");
                }
            }
        }

        // Prix
        for (int i = 0; i < products.size(); i++) {
            price += products.get(i).getUnitPrice() * quantityProduct.get(i);
        }
        this.price = price*App.TVA-client.getDiscount();

        System.out.println("Commande terminée, confirmer l'enregistrement ? ");
        choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("oui") || choice.equals("yes")) {
            Logger.fine("Order Saved in database.");
            this.save();
            System.out.println("Commande validée avec succès.");
        } else if (choice.equalsIgnoreCase("non") || choice.equals("no")) {
            Logger.warning("Order not saved, the content has been reset.");
            this.reset();
            System.out.println("Commande annulée avec succès.");
        } else {
            Logger.severe("Unknown answer, order was reset by default.");
            this.reset();
        }
        return this;
    }

    private void save(){
        for(int i = 0; i < this.products.size(); i++){
            App.getInstance().getProductDAO().save(this.products.get(i));
        }
        this.backupQuantity.clear();
        App.getInstance().getOrderDAO().save(this);
    }

    private void reset(){
        // Pour remettre les quantités disponibles si la commande est annulée.
        for(Product product : backupQuantity.keySet()){
            product.setAvailableQuantity(backupQuantity.get(product));
            App.getInstance().getProductDAO().save(product);
        }
        this.products = new LinkedList<>();
        this.quantityProduct = new LinkedList<>();
        this.price = 0;
        this.client = null;
    }

    /**
     * Stocke les informations d'une commande dans une liste, qui est elle-même mise
     * dans la liste de toutes les commandes.
     * @param data La liste de toutes les commandes que l'on met à jour à chaque appel.
     * @return La liste des commandes mise à jour.
     */
    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getPrice());
        toAdd.add(this.getClient().getName());
        toAdd.add(this.getClient().getAddress().getAddress());
        toAdd.add(this.getClient().getAddress().getCity());
        data.add(toAdd);
        return data;
    }

    /**
     * Stocke le contenu d'une commande dans une liste, qui est elle-même mise
     * dans la liste concernant le contenu de toutes les commandes.
     * @param content La liste avec le contenu de toutes les commandes que l'on met à jour à chaque appel.
     * @return La liste du contenu de toutes les commandes mise à jour.
     */
    public ArrayList<ArrayList<Object>> displayContent(ArrayList<ArrayList<Object>> content) {
        for(Product product : this.products) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(this.getId());
            toAdd.add(product.getName());
            toAdd.add(this.getQuantityProduct().get(this.products.indexOf(product)));
            toAdd.add(product.getUnitPrice());
            content.add(toAdd);
        }
        return content;
    }
}
