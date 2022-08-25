/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.m2i.javaapirest.tpuser.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import fr.m2i.javaapirest.tpuser.model.User;
import fr.m2i.javaapirest.tpuser.util.SessionHelper;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author ben
 */
public class UserDAO {
    private final EntityManager entityManager;
    private List<User> users;
    private Long nextId;

    public UserDAO() {
        this.users = new ArrayList();
        this.nextId = 1L;
        this.entityManager = SessionHelper.getEntityManager();
    }
    
    public List<User> getUsers() {
        Query findAllQuery = entityManager.createQuery("select u from User u");
        System.out.println("findAllQuery.getResultList() "+findAllQuery.getResultList());
        List<User> users = findAllQuery.getResultList();
        return users;
    }
    
      public User findById(Long id) {
        User founded = entityManager.find(User.class, Math.toIntExact(id));

        if (founded == null) {
            System.out.println("Attention l'utilisateur avec l'id : " + id + " n'exsiste pas !");
        }

        return founded;
    }
    
    public boolean create(User user) {
        if (user == null) {
            System.out.println("L'objet user ne peut pas être null");
            return false;
        }

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            
            entityManager.persist(user);
            
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la création de l'utilisateur");
            System.out.println("Exception message : " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }
    
    public boolean update(Long id, User user) {

        User userToUpdate = findById(id);
        
        if (userToUpdate == null) {
            System.out.println("Attention l'utilisteur avec l'id : " + id + " n'exsiste pas !");
            return false;
        }

        userToUpdate.copy(user);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.merge(userToUpdate);

            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite lors de la modification de l'utilisateur");
            System.out.println("Exception message : " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }
    
    public boolean delete(User user) {
        
        if (user == null || user.getId() < 1L) {
            System.out.println("Le produit n'est pas valide !");
            return false;
        }
        
        User userToDelete = findById(Long.valueOf(user.getId()));

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.remove(userToDelete);

            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la suppression");
            System.out.println("Exception message : " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        
    }
    
    public List<User> search(String str,int count) {
System.out.println("search -> str  "+str+" count "+count);
        if (str == null || count <1) {
            System.out.println("Le reference du produit n'est pas valide !");
            return null;
        }

        Query query = entityManager.createQuery("select u from User u where u.lastname = :str OR u.email = :str", User.class);
        query.setParameter("str", str);        
//        query.setParameter("count", count);
        query.setMaxResults(count);
        System.out.println("query.getResultList() -> "+query.getResultList());

        return query.getResultList();
    }
    
}
