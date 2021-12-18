package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

public class FullAddress extends Model {

    @Getter @Setter
    private String address;
    @Getter @Setter
    private String city;

    public FullAddress() {
        super();
    }

    public FullAddress(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        this.setAddress(string(data.get("adresse")));
        this.setCity(string(data.get("ville")));
    }

    /**
     * Stocke les informations d'une adresse dans une liste, qui est elle-même mise
     * dans la liste de toutes les adresses.
     * @param data La liste de toutes les adresses que l'on met à jour à chaque appel.
     * @return La liste des adresses mise à jour.
     */
    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getAddress());
        toAdd.add(this.getCity());
        data.add(toAdd);
        return data;
    }
}
