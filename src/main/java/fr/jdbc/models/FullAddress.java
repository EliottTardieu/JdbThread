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
}
