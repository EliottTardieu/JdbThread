package fr.hibernate.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class Client implements Model {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String forename;

    @Getter @Setter
    @OneToOne
    private FullAddress address;

    @Getter @Setter
    @Column(nullable = true)
    private int discount;

    @Getter @Setter
    @OneToOne(mappedBy = "client")
    private Ordering ordering;

    public Client() {

    }

    public Client(String name, String forename, int discount, FullAddress address) {
        this.name = name;
        this.forename = forename;
        this.discount = discount;
        this.address = address;
    }
}
