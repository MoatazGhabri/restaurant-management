package com.restaurant.dao;

import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    public List<MenuItem> getAllSnacks() throws SQLException {
        List<MenuItem> snacks = new ArrayList<>();
        String sql = "SELECT * FROM snacks";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                snacks.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        }
        return snacks;
    }

    public List<MenuItem> getAllSandwichContents() throws SQLException {
        List<MenuItem> contents = new ArrayList<>();
        String sql = "SELECT * FROM sandwich_contents";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                contents.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        }
        return contents;
    }

    public List<MenuItem> getAllSupplements() throws SQLException {
        List<MenuItem> supplements = new ArrayList<>();
        String sql = "SELECT * FROM supplements";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                supplements.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price")
                ));
            }
        }
        return supplements;
    }

    public MenuItem getSnackById(int id) throws SQLException {
        String sql = "SELECT * FROM snacks WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    public MenuItem getSandwichContentById(int id) throws SQLException {
        String sql = "SELECT * FROM sandwich_contents WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    public MenuItem getSupplementById(int id) throws SQLException {
        String sql = "SELECT * FROM supplements WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }
    public int createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (ticket_number, order_date, total_price,admin_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, order.getTicketNumber());
            stmt.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
            stmt.setDouble(3, order.getTotalPrice());
            stmt.setInt(4, order.getAdminId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        }
    }

    public void addOrderItem(int orderId, OrderItem item) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, item_type, item_id, item_name, item_price) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.setString(2, item.getItemType());
            stmt.setInt(3, item.getItemId());
            stmt.setString(4, item.getItemName());
            stmt.setDouble(5, item.getItemPrice());

            stmt.executeUpdate();
        }
    }

    public Order getOrderByTicketNumber(String ticketNumber) throws SQLException {
        String sql = "SELECT * FROM orders WHERE ticket_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticketNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setTicketNumber(rs.getString("ticket_number"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setTotalPrice(rs.getDouble("total_price"));
                    return order;
                }
            }
        }
        return null;
    }

    public List<OrderItem> getOrderItems(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem item = new OrderItem();
                    item.setId(rs.getInt("id"));
                    item.setOrderId(rs.getInt("order_id"));
                    item.setItemType(rs.getString("item_type"));
                    item.setItemId(rs.getInt("item_id"));
                    item.setItemName(rs.getString("item_name"));
                    item.setItemPrice(rs.getDouble("item_price"));
                    items.add(item);
                }
            }
        }
        return items;
    }
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, a.full_name AS admin_name FROM orders o LEFT JOIN admins a ON o.admin_id = a.id ORDER BY o.order_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTicketNumber(rs.getString("ticket_number"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalPrice(rs.getDouble("total_price"));
                order.setAdminId(rs.getInt("admin_id"));
                order.setAdminName(rs.getString("admin_name"));
                orders.add(order);
            }
        }
        return orders;
    }

    public boolean deleteOrder(int orderId) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // First delete all order items
            String deleteItemsSQL = "DELETE FROM order_items WHERE order_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteItemsSQL)) {
                stmt.setInt(1, orderId);
                stmt.executeUpdate();
            }

            // Then delete the order
            String deleteOrderSQL = "DELETE FROM orders WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteOrderSQL)) {
                stmt.setInt(1, orderId);
                int affectedRows = stmt.executeUpdate();
                conn.commit(); // Commit transaction
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback on error
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true); // Reset auto-commit
                conn.close();
            }
        }
    }

    public boolean updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET ticket_number = ?, order_date = ?, total_price = ?, admin_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getTicketNumber());
            stmt.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
            stmt.setDouble(3, order.getTotalPrice());
            if (order.getAdminId() > 0) {
                stmt.setInt(4, order.getAdminId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, order.getId());

            return stmt.executeUpdate() > 0;
        }
    }
    public Order getOrderWithItems(int orderId) throws SQLException {
        Order order = null;
        String orderSQL = "SELECT * FROM orders WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(orderSQL)) {

            stmt.setInt(1, orderId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setTicketNumber(rs.getString("ticket_number"));
                    order.setOrderDate(rs.getTimestamp("order_date"));
                    order.setTotalPrice(rs.getDouble("total_price"));
                    order.setAdminId(rs.getInt("admin_id"));

                    // Get items - make sure this method works correctly
                    List<OrderItem> items = getOrderItems(orderId);
                    order.setItems(items);

                    // Debug output
                    System.out.println("Found order: " + order.getTicketNumber());
                    System.out.println("With " + items.size() + " items");
                }
            }
        }
        return order;
    }
}