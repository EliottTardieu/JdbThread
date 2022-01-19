package fr.jdbc.utils;

import javax.persistence.EntityManager;

public class DAOUtils {

    public static void begin(EntityManager em) {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    public static void rollback(EntityManager em) {
        if(em.getTransaction().isActive())
            Logger.fine("Rollback");
            em.getTransaction().rollback();
    }

    public static void commit(EntityManager em) {
        if(em.getTransaction().isActive())
            Logger.fine("Commit");
            em.getTransaction().commit();
    }
}
