package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Client {
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
    @Column(nullable = true)
    private int discount;

    @Getter @Setter
    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Ordering ordering;

    public Client() {

    }

    public Client(String name, String forename, int discount, FullAddress address) {
        this.name = name;
        this.forename = forename;
        this.discount = discount;
        this.address = address;
    }

    /**
     * Methode pour créer un client initialisé à partir de saisie dans un terminal.
     *
     * @return Client initialisée
     */
    /*
    public Client initialize() {
        Scanner scanner = new Scanner(System.in);
        String clientAddress;
        String clientCity;

        // Adresse du client
        System.out.println("Entrez l'adresse du client: ");
        clientAddress = scanner.nextLine();
        System.out.println("Entrez la ville du client: ");
        clientCity = scanner.nextLine();
        HashMap<String, Object> criteriasAdd = new HashMap<>();
        criteriasAdd.put("adresse", clientAddress);
        criteriasAdd.put("ville", clientCity);
        if (App.getInstance().getFullAddressDAO().find(criteriasAdd) != null) {
            this.address = App.getInstance().getFullAddressDAO().find(criteriasAdd);
            Logger.fine("Client address found.");
        } else {
            this.address = new FullAddress();
            this.address.setAddress(clientAddress);
            this.address.setCity(clientCity);
            App.getInstance().getFullAddressDAO().save(this.address);
            Logger.warning("Client address unknown, added to database.");
        }

        // Nom et prénom
        System.out.println("Entrez le nom: ");
        this.name = scanner.nextLine();
        System.out.println("Entrez le prénom: ");
        this.forename = scanner.nextLine();

        // Reduction
        System.out.println("Entrez le montant de la réduction: ");
        this.discount = scanner.nextInt();
        scanner.nextLine();

        App.getInstance().getClientDAO().save(this);
        return this;
    }
    */
}
