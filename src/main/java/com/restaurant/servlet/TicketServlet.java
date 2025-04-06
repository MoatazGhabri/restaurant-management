//package com.restaurant.servlet;
//
//import com.restaurant.dao.TicketDAO;
//import com.restaurant.model.Ticket;
//import jakarta.inject.Inject;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/tickets/*")
//public class TicketServlet extends HttpServlet {
//
//    private TicketDAO ticketDAO;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String pathInfo = request.getPathInfo();
//
//        if (pathInfo == null || pathInfo.equals("/")) {
//            // List all tickets
//            request.setAttribute("tickets", ticketDAO.getAllTickets());
//            request.getRequestDispatcher("/WEB-INF/views/ticket/list.jsp").forward(request, response);
//        } else if (pathInfo.equals("/new")) {
//            // Show create form
//            request.getRequestDispatcher("/WEB-INF/views/ticket/form.jsp").forward(request, response);
//        } else {
//            // Show single ticket
//            try {
//                Long ticketId = Long.parseLong(pathInfo.substring(1));
//                Ticket ticket = ticketDAO.getTicket(ticketId);
//                if (ticket != null) {
//                    request.setAttribute("ticket", ticket);
//                    request.getRequestDispatcher("/WEB-INF/views/ticket/show.jsp").forward(request, response);
//                } else {
//                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
//                }
//            } catch (NumberFormatException e) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            }
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String customerName = request.getParameter("customerName");
//        String tableNumberStr = request.getParameter("tableNumber");
//        String specialRequests = request.getParameter("specialRequests");
//
//        if (customerName != null && tableNumberStr != null) {
//            try {
//                Ticket ticket = new Ticket();
//                ticket.setCustomerName(customerName);
//                ticket.setTableNumber(Integer.parseInt(tableNumberStr));
//                ticket.setSpecialRequests(specialRequests);
//
//                ticketDAO.createTicket(ticket);
//                response.sendRedirect(request.getContextPath() + "/tickets");
//            } catch (NumberFormatException e) {
//                request.setAttribute("error", "Invalid table number");
//                request.getRequestDispatcher("/WEB-INF/views/ticket/form.jsp").forward(request, response);
//            }
//        } else {
//            request.setAttribute("error", "Customer name and table number are required");
//            request.getRequestDispatcher("/WEB-INF/views/ticket/form.jsp").forward(request, response);
//        }
//    }
//    @Override
//    public void init() throws ServletException {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantPU");
//        EntityManager em = emf.createEntityManager();
//        this.ticketDAO = new TicketDAO(em); // Il faut que le DAO accepte un EntityManager en constructeur
//    }
//
//
//}
