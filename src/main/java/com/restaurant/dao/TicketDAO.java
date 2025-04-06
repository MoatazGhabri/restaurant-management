//package com.restaurant.dao;
//
//import com.restaurant.model.Ticket;
//import jakarta.persistence.EntityManager;
//
//import java.util.List;
//
//public class TicketDAO {
//    private final EntityManager entityManager;
//
//    public TicketDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public void createTicket(Ticket ticket) {
//        entityManager.getTransaction().begin();
//        entityManager.persist(ticket);
//        entityManager.getTransaction().commit();
//    }
//
//    public List<Ticket> getAllTickets() {
//        return entityManager.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
//    }
//
//    public Ticket getTicket(Long id) {
//        return entityManager.find(Ticket.class, id);
//    }
//}
