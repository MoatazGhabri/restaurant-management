package com.restaurant.controller;

import com.restaurant.dao.MenuItemDAO;
import com.restaurant.model.Admin;
import com.restaurant.model.MenuItem;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private MenuItemDAO menuItemDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        menuItemDAO = new MenuItemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("login");
            return;
        }
        try {
            List<MenuItem> snacks = menuItemDAO.getAllSnacks();
            List<MenuItem> contents = menuItemDAO.getAllSandwichContents();
            List<MenuItem> supplements = menuItemDAO.getAllSupplements();

            request.setAttribute("snacks", snacks);
            request.setAttribute("contents", contents);
            request.setAttribute("supplements", supplements);

            request.getRequestDispatcher("/orderForm.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving menu items", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        try {
            int snackId = Integer.parseInt(request.getParameter("snack"));
            int contentId = Integer.parseInt(request.getParameter("content"));
            int supplementId = Integer.parseInt(request.getParameter("supplement"));

            MenuItem snack = menuItemDAO.getSnackById(snackId);
            MenuItem content = menuItemDAO.getSandwichContentById(contentId);
            MenuItem supplement = menuItemDAO.getSupplementById(supplementId);

            double totalPrice = snack.getPrice() + content.getPrice() + supplement.getPrice();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String datePart = dateFormat.format(new Date());
            String randomPart = String.format("%04d", new Random().nextInt(10000));
            String ticketNumber = "TKT-" + datePart + "-" + randomPart;

            Order order = new Order(ticketNumber, new Date(), totalPrice);
            if (admin != null) {
                order.setAdminId(admin.getId());
            }

            System.out.println("Creating order: " + order);

            int orderId = menuItemDAO.createOrder(order);

            menuItemDAO.addOrderItem(orderId,
                    new OrderItem("SNACK", snack.getId(), snack.getName(), snack.getPrice()));
            menuItemDAO.addOrderItem(orderId,
                    new OrderItem("CONTENT", content.getId(), content.getName(), content.getPrice()));
            menuItemDAO.addOrderItem(orderId,
                    new OrderItem("SUPPLEMENT", supplement.getId(), supplement.getName(), supplement.getPrice()));

            Order completeOrder = menuItemDAO.getOrderByTicketNumber(ticketNumber);
            completeOrder.setItems(menuItemDAO.getOrderItems(orderId));

            request.setAttribute("order", completeOrder);
            request.setAttribute("admin", admin);
            request.getRequestDispatcher("/orderResult.jsp").forward(request, response);

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("Error processing order", e);
        }
    }
}