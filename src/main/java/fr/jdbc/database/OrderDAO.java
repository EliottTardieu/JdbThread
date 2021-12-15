package fr.jdbc.database;

import fr.jdbc.models.Order;
import fr.jdbc.models.Product;

import java.util.HashMap;

public class OrderDAO extends DAO<Order> {

    public OrderDAO() {
        super(Order.class);
    }

    /**
     * Retourne le nom de la table correspondant a l'objet T
     *
     * @return Le nom d'une table SQL
     */
    @Override
    protected String tableName() {
        return "Commandes";
    }

    /**
     * Retourne une map associant une colonne SQL a sa valeur dans le cas d'un insert
     *
     * @param object Objet a mettre a jour
     * @return Une Map associant une colonne a sa valeur
     * @see this.insert
     * @see this.save
     */
    @Override
    protected HashMap<String, Object> getInsertMap(Order object) {
        HashMap<String, Object> insertMap = new HashMap<>();
        String concatProds = "";
        String concatQts = "";
        for(Product product : object.getProducts()){
            concatProds = concatProds.concat(String.valueOf(product.getId()));
            if(product != object.getProducts().getLast()){
                concatProds = concatProds.concat(",");
            }
        }
        for(int quantities : object.getQuantityProduct()){
            concatQts = concatQts.concat(String.valueOf(quantities));
            if(quantities != object.getQuantityProduct().getLast()){
                concatQts = concatQts.concat(",");
            }
        }
        insertMap.put("id_produits", concatProds);
        insertMap.put("quantite_produits", concatQts);
        insertMap.put("prix", object.getPrice());
        insertMap.put("id_client", object.getClient().getId());
        return insertMap;
    }

    /**
     * Retourne une map associant une colonne SQL a sa valeur dans le cas d'un update
     *
     * @param object Objet a mettre a jour
     * @return Une Map associant une colonne a sa valeur
     * @see this.update
     * @see this.save
     */
    @Override
    protected HashMap<String, Object> getUpdateMap(Order object) {
        HashMap<String, Object> updateMap = new HashMap<>();
        String concatProds = "";
        String concatQts = "";
        for(Product product : object.getProducts()){
            concatProds = concatProds.concat(String.valueOf(product.getId()));
            if(product != object.getProducts().getLast()){
                concatProds = concatProds.concat(",");
            }
        }
        for(int quantities : object.getQuantityProduct()){
            concatQts = concatQts.concat(String.valueOf(quantities));
            if(quantities != object.getQuantityProduct().getLast()){
                concatQts = concatQts.concat(",");
            }
        }
        updateMap.put("id_produits", concatProds);
        updateMap.put("quantite_produits", concatQts);
        updateMap.put("prix", object.getPrice());
        updateMap.put("id_client", object.getClient().getId());
        return updateMap;
    }
}
