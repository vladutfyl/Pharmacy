package model.repository;

import model.entity.Drug;
import utils.query.MedsQueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DrugsRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertMed(Drug meds) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(meds);
        em.getTransaction().commit();
        em.close();
    }

    public void updateCant(Drug meds, int newCant ){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.UPDATE_CANT_QUERY).setParameter("cant", newCant).setParameter("name", meds.getName());
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public Drug checkNameMeds(String nume){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.NAME_QUERY, Drug.class).setParameter("name", nume);
        List<Drug> meds = (List<Drug>)jpqlQuery.getResultList();
        em.getTransaction().commit();
        em.close();
        if(!meds.isEmpty()) {
            return meds.get(0);
        }
        else {
            return null;
        }
    }


    public void updatePrice(String meds, float newPrice ){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.UPDATE_PRICE_QUERY).setParameter("price", newPrice).setParameter("name", meds);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }


    public List<Drug> getMeds(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.MED_SELECT_QUERY);
        List<Drug> meds = jpqlQuery.getResultList();
        em.getTransaction().commit();
        em.close();
        return meds;
    }

    public void deleteMedByName(String nume){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.DELETE_NUME_QUERY).setParameter("name", nume);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateRatingAndDiscount(Drug d, float rating, int discount, int qunatityDiscounted) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.UPDATE_RATING_DISCOUNT_QUERY).setParameter("discount",discount).setParameter("quantityDiscounted",qunatityDiscounted).setParameter("rating", rating).setParameter("name", d.getName());
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateRating(Drug d, float newRating, int newNrReviews) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(MedsQueryUtils.UPDATE_RATING_QUERY).setParameter("rating",newRating).setParameter("nrReviews",newNrReviews).setParameter("name",d.getName());
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
