//package com.restaurant.dao;
//
//import com.restaurant.model.Order;
//import jakarta.ejb.Stateless;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import java.util.List;
//
//@Stateless
//public class OrderDAO {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void create(Order order) {
//        entityManager.persist(order);
//    }
//
//    public Order findById(Long id) {
//        return entityManager.find(Order.class, id);
//    }
//
//    public List<Order> findAll() {
//        return entityManager.createQuery("SELECT o FROM Order o", Order.class)
//                .getResultList();
//    }
//
//    public List<Order> findByTicketId(Long ticketId) {
//        return entityManager.createQuery(
//                        "SELECT o FROM Order o WHERE o.ticket.id = :ticketId",
//                        Order.class
//                )
//                .setParameter("ticketId", ticketId)
//                .getResultList();
//    }
//
//    public void update(Order order) {
//        entityManager.merge(order);
//    }
//
//    public void delete(Long id) {
//        Order order = findById(id);
//        if (order != null) {
//            entityManager.remove(order);
//        }
//    }
//}
