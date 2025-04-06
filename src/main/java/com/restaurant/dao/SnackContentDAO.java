//package com.restaurant.dao;
//
//import com.restaurant.model.SnackContent;
//
//import jakarta.persistence.EntityManager;
//import java.util.List;
//import jakarta.persistence.PersistenceContext;
//public class SnackContentDAO {
//    @PersistenceContext
//
//    private EntityManager em;
//
//    public SnackContentDAO(EntityManager em) {
//        this.em = em;
//    }
//
//
//    public List<SnackContent> getAllSnackContents() {
//       return em.createQuery("SELECT s FROM SnackContent s", SnackContent.class).getResultList();
//    }
//    public SnackContent getSnackContent(int id) {
//        return em.find(SnackContent.class, id);
//    }
//}
