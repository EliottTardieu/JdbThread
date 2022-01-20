package fr.hibernate.controllers;

import fr.hibernate.database.DAO;
import fr.hibernate.models.Model;

import javax.persistence.EntityManager;
import java.util.ArrayList;

public abstract class Controller<T extends Model> {

    private final Class<T> type;

    protected final ArrayList<T> models = new ArrayList<>();

    protected Controller(Class<T> type) {
        this.type = type;
    }

    protected T save(EntityManager em,DAO<T> DAO, T object, boolean autoCommit) {
        this.models.add(object);

        if (autoCommit) {
            DAO.save(em, object);
        } else {
            DAO.persist(em, object);
        }

        if (em.contains(object)) {
            return object;
        } else {
            return null;
        }
    }
}
