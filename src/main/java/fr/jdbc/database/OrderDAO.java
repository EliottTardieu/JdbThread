package fr.jdbc.database;

import fr.jdbc.models.Order;

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
        insertMap.put("id_produits", object);
        insertMap.put("quantite_produits", object);
        insertMap.put("prix", object);
        insertMap.put("id_client", object);
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
        updateMap.put("id_produits", object);
        updateMap.put("quantite_produits", object);
        updateMap.put("prix", object);
        updateMap.put("id_client", object);
        return updateMap;
    }
}
