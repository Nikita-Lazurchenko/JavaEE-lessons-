package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.FlightService;
import org.example.utils.JSPHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/flight")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("flights",flightService.findAll());
        req.getRequestDispatcher(JSPHelper.getPath("flights.jsp")).forward(req, resp);
    }
}
