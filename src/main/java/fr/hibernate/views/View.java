package fr.hibernate.views;

import javax.persistence.EntityManager;

public interface View {

    public void displayAll(EntityManager em);
}
