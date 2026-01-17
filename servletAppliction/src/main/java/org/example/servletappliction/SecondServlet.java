package org.example.servletappliction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String param1 = req.getParameter("param1");

        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Param 1</h1>");

        Map<String,String[]> parametrMap = req.getParameterMap();
        parametrMap.entrySet().forEach(e -> writer.println(e.getKey() + "=" + e.getValue()[0]));

    }
}
