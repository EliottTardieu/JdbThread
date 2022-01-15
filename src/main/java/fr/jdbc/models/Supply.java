package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
public class Supply {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    @OneToMany(mappedBy = "supply", cascade = CascadeType.ALL)
    private List<Product> products;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    @Getter @Setter
    private float price;

    public Supply() {
        this.products = new ArrayList<>();
    }

    public Supply(Supplier supplier, float price, ArrayList<Product> products) {
        this.supplier = supplier;
        this.price = price;
        this.products = products;
    }

    /*
    private void reset() {
        // Pour remettre les quantités disponibles si la commande est annulée.
        this.products = new ArrayList<>();
        this.price = 0;
        this.supplier = null;
    }
    */
}
