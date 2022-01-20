package fr.hibernate.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ordering implements Model {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private float price;

    @Getter @Setter
    @OneToOne
    private Client client;

    @Getter @Setter
    @OneToMany(mappedBy = "ordering")
    private Set<OrderContent> orderContents;

    public Ordering() {
        this.orderContents = new HashSet<>();
    }

    public Ordering(float price, Client client, Set<OrderContent> orderContents) {
        this.price = price;
        this.client = client;
        this.orderContents = orderContents;
    }
}
