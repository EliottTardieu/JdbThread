package fr.jdbc.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class FullAddress extends Model {

    @Getter @Setter
    private String address;
    @Getter @Setter
    private String city;

    public FullAddress() {
        super();
    }

    public FullAddress(HashMap<String, Object> data) {
        super(data);
    }

    @Override
    protected void hydrate(HashMap<String, Object> data) {
        this.setId(integer(data.get("id")));
        this.setAddress(string(data.get("adresse")));
        this.setCity(string(data.get("ville")));
    }
}
