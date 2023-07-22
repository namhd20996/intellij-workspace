package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/home-page", "/bai-3-4", "/bai-2", "/bai-5"}, name = "ofHome")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/home-page":
                doGetHomePage(req, resp);
                break;
            case "/bai-3-4":
                doGetHomePageTest(req, resp);
                break;
            case "/bai-2":
                doGetBai_2(req, resp);
                break;
            case "/bai-5":
                doGetBai_5(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/calculator":
                doPostCalculator(req, resp);
                break;
            case "/bai-3-4":
                doPostBai_3_4(req, resp);
                break;
            case "/bai-5":
                doPostBai_5(req, resp);
                break;
        }
    }

    protected void doGetHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

    protected void doGetHomePageTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/bai-3-4/home.jsp").forward(req, resp);
    }

    protected void doGetBai_2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Hello FPT..</h1>");
    }

    protected void doGetBai_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/bai-5/home.jsp").forward(req, resp);
    }

    protected void doPostBai_5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String length = req.getParameter("length");
        String width = req.getParameter("width");

        double a = Double.valueOf(length);
        double b = Double.valueOf(width);

        double acreage = a * b;
        double perimeter = (a + b) * 2;

        req.setAttribute("acreage",acreage);
        req.setAttribute("perimeter",perimeter);

        getServletContext().getRequestDispatcher("/WEB-INF/views/bai-5/home.jsp").forward(req, resp);
    }

    protected void doPostCalculator(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

    protected void doPostBai_3_4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/WEB-INF/views/bai-3-4/home.jsp").forward(req, resp);
    }
}
