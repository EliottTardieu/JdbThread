package fr.jdbc.database;

import fr.jdbc.models.Product;
import fr.jdbc.models.Supplier;

import java.util.HashMap;

public class SupplierDAO extends DAO<Supplier> {

    public SupplierDAO() {
        super(Supplier.class);
    }

    /**
     * Retourne le nom de la table correspondant a l'objet T
     *
     * @return Le nom d'une table SQL
     */
    @Override
    protected String tableName() {
        return "Fournisseurs";
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
    protected HashMap<String, Object> getInsertMap(Supplier object) {
        HashMap<String, Object> insertMap = new HashMap<>();
        String concatProds = "";
        for(int i = 0; i < object.getProducts().size(); i++) {
            concatProds = concatProds.concat(String.valueOf(object.getProducts().get(i).getId()));
            if(i != object.getProducts().size()-1){
                concatProds = concatProds.concat(",");
            }
        }
        insertMap.put("nom", object.getName());
        insertMap.put("prenom", object.getForename());
        insertMap.put("id_produits", concatProds);
        insertMap.put("adresse", object.getAddress().getId());
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
    protected HashMap<String, Object> getUpdateMap(Supplier object) {
        HashMap<String, Object> updateMap = new HashMap<>();
        String concatProds = "";
        for(int i = 0; i < object.getProducts().size(); i++) {
            concatProds = concatProds.concat(String.valueOf(object.getProducts().get(i).getId()));
            if(i != object.getProducts().size()-1){
                concatProds = concatProds.concat(",");
            }
        }
        updateMap.put("nom", object.getName());
        updateMap.put("prenom", object.getForename());
        updateMap.put("id_produits", concatProds);
        updateMap.put("adresse", object.getAddress().getId());
        return updateMap;
    }
}
