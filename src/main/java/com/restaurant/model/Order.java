package com.restaurant.model;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private String ticketNumber;
    private Date orderDate;
    private double totalPrice;
    private List<OrderItem> items;
    private int adminId;
    private String adminName;

    public Order() {}



    public Order(String ticketNumber, Date orderDate, double totalPrice) {
        this.ticketNumber = ticketNumber;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}