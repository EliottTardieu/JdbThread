package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Entity
public class Supply implements Model {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    @OneToMany(mappedBy = "supply")
    private List<Product> products;

    @Getter @Setter
    @OneToOne
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
}
