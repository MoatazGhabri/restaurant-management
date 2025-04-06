package com.restaurant.controller;

import com.restaurant.dao.MenuItemDAO;
import com.restaurant.model.Admin;
import com.restaurant.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/orders")
public class OrderControllerServlet extends HttpServlet {
    private MenuItemDAO menuItemDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        menuItemDAO = new MenuItemDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            response.sendRedirect("login");
            return;
        }
        try {
            String action = request.getParameter("action");

            if ("delete".equals(action)) {
                int orderId = Integer.parseInt(request.getParameter("id"));
                menuItemDAO.deleteOrder(orderId);
                response.sendRedirect("orders");
                return;
            }
            if ("view".equals(action)) {
                int orderId = Integer.parseInt(request.getParameter("id"));
                System.out.println("DEBUG: Fetching order with ID: " + orderId);

                Order order = menuItemDAO.getOrderWithItems(orderId);

                if (order == null) {
                    System.out.println("DEBUG: No order found with ID: " + orderId);
                    request.setAttribute("error", "Order not found");
                } else {
                    System.out.println("DEBUG: Found order - Ticket: " + order.getTicketNumber());
                    System.out.println("DEBUG: Item count: " + order.getItems().size());
                    request.setAttribute("order", order);
                }

                request.getRequestDispatcher("/orderDetails.jsp").forward(request, response);
                return;
            }
            List<Order> orders = menuItemDAO.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/orderList.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("ERROR in doGet: " + e.getMessage()); // Debug line
            e.printStackTrace();
            request.setAttribute("error", "Error loading order: " + e.getMessage());
            request.getRequestDispatcher("/orderDetails.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        try {
            int orderId = Integer.parseInt(request.getParameter("id"));
            String ticketNumber = request.getParameter("ticketNumber");
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

            Order order = new Order();
            order.setId(orderId);
            order.setTicketNumber(ticketNumber);
            order.setTotalPrice(totalPrice);
            order.setOrderDate(new Date());

            if (menuItemDAO.updateOrder(order)) {
                response.sendRedirect("orders");
            } else {
                throw new ServletException("Failed to update order");
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(e);
        }
    }
}