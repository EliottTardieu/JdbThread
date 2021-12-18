package fr.jdbc.models;

import dnl.utils.text.table.TextTable;
import fr.jdbc.App;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Model {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String forename;
    @Getter @Setter
    private FullAddress address;
    @Getter @Setter
    private int discount;

    public Client() {
        super();
    }

    public Client(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        this.setName(string(data.get("nom")));
        this.setForename(string(data.get("prenom")));
        this.setDiscount(integer(data.get("reduction")));
        this.setAddress(App.getInstance().getFullAddressDAO().findById(integer(data.get("adresse"))));
    }

    /**
     * Stocke les informations d'un client dans une liste, qui est elle-même mise
     * dans la liste de tous les clients.
     * @param data La liste de tous les clients que l'on met à jour à chaque appel.
     * @return La liste des clients mise à jour.
     */
    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getName());
        toAdd.add(this.getForename());
        toAdd.add(this.getDiscount());
        toAdd.add(this.getAddress().getAddress());
        toAdd.add(this.getAddress().getCity());
        data.add(toAdd);
        return data;
    }
}
