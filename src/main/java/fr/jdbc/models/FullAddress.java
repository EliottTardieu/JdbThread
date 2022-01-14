package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class FullAddress {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String city;

    @Getter @Setter
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Client client;

    @Getter @Setter
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private Supplier supplier;

    public FullAddress() {

    }

    public FullAddress(String address, String city) {
        this.address = address;
        this.city = city;
    }

    /**
     * Stocke les informations d'une adresse dans une liste, qui est elle-même mise
     * dans la liste de toutes les adresses.
     *
     * @param data La liste de toutes les adresses que l'on met à jour à chaque appel.
     * @return La liste des adresses mise à jour.
     */
    /*
    public ArrayList<ArrayList<Object>> display(ArrayList<ArrayList<Object>> data) {
        ArrayList<Object> toAdd = new ArrayList<>();
        toAdd.add(this.getId());
        toAdd.add(this.getAddress());
        toAdd.add(this.getCity());
        data.add(toAdd);
        return data;
    }
    */
}
