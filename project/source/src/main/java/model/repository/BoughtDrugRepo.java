package model.repository;

import model.entity.BoughtDrug;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BoughtDrugRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void add(BoughtDrug boughtDrug) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(boughtDrug);
        em.getTransaction().commit();
        em.close();
    }
}
