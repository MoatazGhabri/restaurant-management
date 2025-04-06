//package com.restaurant.model;
//
//import jakarta.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "tickets")
//public class Ticket {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = true)
//    private String customerName;
//
//    @Column(nullable = true)
//    private Integer tableNumber;
//
//    @Column(nullable = true)
//    private String status;
//
//    @Column(nullable = true)
//    private LocalDateTime createdAt;
//
//    @Column
//    private String specialRequests;
//
//    public Ticket() {
//        this.createdAt = LocalDateTime.now();
//        this.status = "PENDING";
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public Integer getTableNumber() {
//        return tableNumber;
//    }
//
//    public void setTableNumber(Integer tableNumber) {
//        this.tableNumber = tableNumber;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getSpecialRequests() {
//        return specialRequests;
//    }
//
//    public void setSpecialRequests(String specialRequests) {
//        this.specialRequests = specialRequests;
//    }
//}