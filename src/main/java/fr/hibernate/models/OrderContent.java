package fr.hibernate.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@IdClass(OrderContentId.class)
public class OrderContent implements Model {

    @Getter
    @Id @ManyToOne
    @JoinColumn(name = "ordering_id")
    private Ordering ordering;

    @Getter
    @Id @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Getter @Setter
    private int quantity;

    public OrderContent() {

    }

    public OrderContent(Ordering ordering, Product product, int quantity) {
        this.ordering = ordering;
        this.product = product;
        this.quantity = quantity;
    }
}
