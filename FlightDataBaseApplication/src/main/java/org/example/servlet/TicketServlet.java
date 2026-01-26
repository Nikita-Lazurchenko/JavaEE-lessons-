package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.TicketDao;
import org.example.service.TicketService;
import org.example.utils.JSPHelper;

import java.io.IOException;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Integer id  = Integer.valueOf(req.getParameter("id"));

        req.setAttribute("tickets",ticketService.findAllByFlightId(id));
        req.getRequestDispatcher(JSPHelper.getPath("tickets.jsp")).forward(req, resp);

    }
}
