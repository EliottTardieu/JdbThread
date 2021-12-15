package fr.jdbc.database;

import fr.jdbc.models.Supply;

import java.util.HashMap;

public class SupplyDAO extends DAO<Supply> {

    public SupplyDAO() {
        super(Supply.class);
    }

    /**
     * Retourne le nom de la table correspondant a l'objet T
     *
     * @return Le nom d'une table SQL
     */
    @Override
    protected String tableName() {
        return "Fournitures";
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
    protected HashMap<String, Object> getInsertMap(Supply object) {
        HashMap<String, Object> insertMap = new HashMap<>();
        String concatProds = "";
        for(int i = 0; i < object.getProducts().size(); i++) {
            concatProds = concatProds.concat(String.valueOf(object.getProducts().get(i).getId()));
            if(i != object.getProducts().size()-1){
                concatProds = concatProds.concat(",");
            }
        }
        insertMap.put("id_produits", concatProds);
        insertMap.put("prix", object.getPrice());
        insertMap.put("id_fournisseur", object.getSupplier().getId());
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
    protected HashMap<String, Object> getUpdateMap(Supply object) {
        HashMap<String, Object> updateMap = new HashMap<>();
        String concatProds = "";
        for(int i = 0; i < object.getProducts().size(); i++) {
            concatProds = concatProds.concat(String.valueOf(object.getProducts().get(i).getId()));
            if(i != object.getProducts().size()-1){
                concatProds = concatProds.concat(",");
            }
        }
        updateMap.put("id_produits", concatProds);
        updateMap.put("prix", object.getPrice());
        updateMap.put("id_fournisseur", object.getSupplier().getId());
        return updateMap;
    }
}
