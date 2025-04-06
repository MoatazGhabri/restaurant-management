//package com.restaurant.dao;
//
//import com.restaurant.model.Supplement;
//import jakarta.persistence.PersistenceContext;
//
//import jakarta.persistence.EntityManager;
//import java.util.List;
//public class SupplementDAO {
//    @PersistenceContext
//
//    private EntityManager entityManager;
//
//    public SupplementDAO(EntityManager em) {
//        this.entityManager = em;
//    }
//
//    public List<Supplement> getAllSupplements() {
//        return entityManager.createQuery("SELECT s FROM Supplement s", Supplement.class).getResultList();
//    }
//
//    public Supplement getSupplement(int id) {
//        return entityManager.find(Supplement.class, id);
//    }
//}
