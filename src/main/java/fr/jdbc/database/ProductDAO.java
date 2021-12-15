package fr.jdbc.database;

import fr.jdbc.models.Product;

import java.util.HashMap;

public class ProductDAO extends DAO<Product> {

    public ProductDAO() {
        super(Product.class);
    }

    /**
     * Retourne le nom de la table correspondant a l'objet T
     *
     * @return Le nom d'une table SQL
     */
    @Override
    protected String tableName() {
        return "Produits";
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
    protected HashMap<String, Object> getInsertMap(Product object) {
        HashMap<String, Object> insertMap = new HashMap<>();
        insertMap.put("nom", object.getName());
        insertMap.put("categorie", object.getCategory());
        insertMap.put("espece", object.getSpecies());
        insertMap.put("prix_unitaire", object.getUnitPrice());
        insertMap.put("quantite_disponible", object.getAvailableQuantity());
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
    protected HashMap<String, Object> getUpdateMap(Product object) {
        HashMap<String, Object> updateMap = new HashMap<>();
        updateMap.put("nom", object.getName());
        updateMap.put("categorie", object.getCategory());
        updateMap.put("espece", object.getSpecies());
        updateMap.put("prix_unitaire", object.getUnitPrice());
        updateMap.put("quantite_disponible", object.getAvailableQuantity());
        return updateMap;        }
}
