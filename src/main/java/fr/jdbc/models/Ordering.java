package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ordering {
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private float price;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    @Getter @Setter
    @OneToMany(mappedBy = "ordering", cascade = CascadeType.ALL)
    private Set<OrderContent> orderContents;

    public Ordering() {
        this.orderContents = new HashSet<>();
    }

    public Ordering(float price, Client client, Set<OrderContent> orderContents) {
        this.price = price;
        this.client = client;
        this.orderContents = orderContents;
    }
    /*
    private void save() {
        for (int i = 0; i < this.products.size(); i++) {
            App.getInstance().getProductDAO().save(this.products.get(i));
        }
        this.backupQuantity.clear();
        App.getInstance().getOrderDAO().save(this);
    }*/
}
