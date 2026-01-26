package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/cookies")
public class CookiesServlet extends HttpServlet {
    private static final String UNIQUE_USER = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = req.getCookies();
        if (cookies == null ||
                Arrays.stream(cookies).filter(cookie -> UNIQUE_USER
                        .equals(cookie.getName())).findAny().isEmpty())
        {
            var cookie = new Cookie(UNIQUE_USER, "1");
            cookie.setPath("/cookies");
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);
        }
    }
}
