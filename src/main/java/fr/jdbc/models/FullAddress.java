package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class FullAddress implements Model {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String city;

    @Getter @Setter
    @OneToOne(mappedBy = "address")
    private Client client;

    @Getter @Setter
    @OneToOne(mappedBy = "address")
    private Supplier supplier;

    public FullAddress() {

    }

    public FullAddress(String address, String city) {
        this.address = address;
        this.city = city;
    }
}
