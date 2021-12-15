package fr.jdbc.database;

import fr.jdbc.models.Client;

import java.util.HashMap;

public class ClientDAO extends DAO<Client> {


    public ClientDAO() {
        super(Client.class);
    }

    /**
     * Retourne le nom de la table correspondant a l'objet T
     *
     * @return Le nom d'une table SQL
     */
    @Override
    protected String tableName() {
        return "Clients";
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
    protected HashMap<String, Object> getInsertMap(Client object) {
        HashMap<String, Object> insertMap = new HashMap<>();
        insertMap.put("nom", object.getName());
        insertMap.put("prenom", object.getForename());
        insertMap.put("reduction", object.getDiscount());
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
    protected HashMap<String, Object> getUpdateMap(Client object) {
        HashMap<String, Object> updateMap = new HashMap<>();
        updateMap.put("nom", object.getName());
        updateMap.put("prenom", object.getForename());
        updateMap.put("reduction", object.getDiscount());
        updateMap.put("adresse", object.getAddress().getId());
        return updateMap;
    }
}
