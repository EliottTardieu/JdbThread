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
        return null;
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
        return null;
    }
}
