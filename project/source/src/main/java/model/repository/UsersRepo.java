package model.repository;

import model.entity.User;
import utils.query.UsersQueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class UsersRepo {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertUser(User users){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(users);
        em.getTransaction().commit();
        em.close();
    }


    public boolean checkUsername(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UsersQueryUtils.USER_BY_USERNAME_QUERY, User.class).setParameter("username", username);
        List<User> users =(List<User>) jpqlQuery.getResultList();
        em.getTransaction().commit();
        em.close();
        return !users.isEmpty();
    }

    public boolean checkEmail(String email) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UsersQueryUtils.USER_BY_EMAIL_QUERY, User.class).setParameter("email", email);
        List<User> users =(List<User>) jpqlQuery.getResultList();
        em.getTransaction().commit();
        em.close();
        return !users.isEmpty();
    }

    public boolean checkLogin(String username, String password){
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Query jpqlQuery = em.createQuery(UsersQueryUtils.USER_LOGIN_QUERY, User.class).setParameter("username", username).setParameter("password", password);
            List<User> users =(List<User>) jpqlQuery.getResultList();
            em.getTransaction().commit();
            em.close();
            return !users.isEmpty();
    }


    public User getCurrentUser(String username){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UsersQueryUtils.USER_BY_USERNAME_QUERY, User.class).setParameter("username", username);
        User user =(User) jpqlQuery.getResultList().get(0);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public void updateUser(String username,String password,String email,String name,float money){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UsersQueryUtils.UPDATE_USER_QUERY).
                setParameter("email",email).
                setParameter("username", username).
                setParameter("name", name).
                setParameter("money",money).
                setParameter("password", password);
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteUser(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UsersQueryUtils.DELETE_USER_QUERY).setParameter("idUser", user.getIdUser());
        jpqlQuery.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<User> getUsers(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query jpqlQuery = em.createQuery(UsersQueryUtils.USER_SELECT_QUERY);
        List<User> users =(List<User>) jpqlQuery.getResultList();
        em.getTransaction().commit();
        em.close();
        return users;
    }

}
