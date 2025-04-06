//package com.restaurant.dao;
//
//import com.restaurant.model.Snack;
//
//import java.util.List;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import com.restaurant.model.Snack;
//
//public class SnackDAO {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public SnackDAO(EntityManager em) {
//        this.entityManager = em;
//    }
//
//    public List<Snack> getAllSnacks() {
//        return entityManager.createQuery("SELECT s FROM Snack s", Snack.class).getResultList();
//    }
//
//    public Snack getSnack(int id) {
//        return entityManager.find(Snack.class, id);
//    }
//}
