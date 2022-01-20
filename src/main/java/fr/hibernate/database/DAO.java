package fr.hibernate.database;

import fr.hibernate.models.Model;
import fr.hibernate.utils.DAOUtils;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class DAO<T extends Model> {

    private final Class<T> type;

    public DAO(Class<T> clientClass) {
        type = clientClass;
    }

    public void persist(EntityManager em, T model) {
        DAOUtils.begin(em);
        em.persist(model);
        em.flush();
    }

    public void save(EntityManager em, T model) {
        DAOUtils.begin(em);
        em.persist(model);
        DAOUtils.commit(em);
    }

    public void remove(EntityManager em, T model) {
        DAOUtils.begin(em);
        em.remove(model);
        DAOUtils.commit(em);
    }

    public List<T> getAll(EntityManager em) {
        return em.createQuery("from "+type.getSimpleName()).getResultList();
    }
}
