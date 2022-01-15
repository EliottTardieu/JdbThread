package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Supplier {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String forename;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private FullAddress address;

    @Getter @Setter
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products;

    @Getter @Setter
    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL)
    private Supply supply;

    public Supplier() {
        this.products = new ArrayList<>();
    }

    public Supplier(String name, String forename, FullAddress address) {
        this.products = new ArrayList<>();
        this.name = name;
        this.forename = forename;
        this.address = address;
    }
}
