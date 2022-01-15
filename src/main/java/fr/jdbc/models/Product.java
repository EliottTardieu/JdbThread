package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Product {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String category;

    @Getter @Setter
    private String species;

    @Getter @Setter
    private float unitPrice;

    @Getter @Setter
    private int availableQuantity;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    private Supply supply;

    @Getter @Setter
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderContent> orderContents;

    public Product() {
        this.orderContents = new HashSet<>();
    }

    public Product(String name, String category, String species, float unitPrice, int availableQuantity) {
        this.orderContents = new HashSet<>();
        this.name = name;
        this.category = category;
        this.species = species;
        this.unitPrice = unitPrice;
        this.availableQuantity = availableQuantity;
    }
}
