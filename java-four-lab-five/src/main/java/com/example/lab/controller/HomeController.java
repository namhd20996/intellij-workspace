package com.example.lab.controller;

import com.example.lab.dto.UserDTO;
import com.example.lab.service.UserService;
import com.example.lab.util.FormUtil;
import com.example.lab.util.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/home-page", "/login", "/user/create", "/user/update", "/user/delete", "/user/edit", "/logout"}, name = "ofHome")
public class HomeController extends HttpServlet {

    @Inject
    private UserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        final String url = "/WEB-INF/views/";
        switch (action) {
            case "/home-page":
                doGetUtil(req, resp, url + "home.jsp");
                break;
            case "/login":
                doGetUtil(req, resp, url + "login.jsp");
                break;
            case "/user/edit":
                doGetEdit(req, resp, url + "home.jsp");
                break;
            case "/user/delete":
                doGetDelete(req, resp, url + "home.jsp");
                break;
            case "/logout":
                doGetLogout(req, resp, url + "login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                doPostLogin(req, resp);
                break;
            case "/user/create":
                doPostCreate(req, resp);
                break;
            case "/user/update":
                doPostUpdate(req, resp);
                break;
            case "/user/delete":
                doGetDelete(req, resp, "/WEB-INF/views/home.jsp");
                break;
        }
    }

    protected void doGetLogout(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        SessionUtil.getInstance().removeValue(req, "User");
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    protected void doGetUtil(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        req.setAttribute("users", service.findAll());
        req.setAttribute("message", req.getParameter("message"));
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        UserDTO user = service.findOneById(req.getParameter("id"));
        req.setAttribute("user", user);
        req.setAttribute("users", service.findAll());
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        service.delete(req.getParameter("id"));
        req.setAttribute("users", service.findAll());
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    protected void doPostLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO user = service.findOneByUsernameAndPassword(req.getParameter("username"), req.getParameter("password"));
        String url = "/WEB-INF/views/login.jsp";
        String message = "Fail..";
        if (user != null) {
            url = "/WEB-INF/views/home.jsp";
            message = "Success";
            SessionUtil.getInstance().putValue(req, "User", user);
            req.setAttribute("users", service.findAll());
        }
        req.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

    protected void doPostCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserDTO user = FormUtil.toModel(UserDTO.class, req);
        if (service.findOneById(user.getId()) != null) {
            resp.sendRedirect(req.getContextPath() + "/home-page?message=Username is exists");
        } else {
            if (service.create(user) != null) {
                resp.sendRedirect(req.getContextPath() + "/home-page?message=Success");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home-page?message=Fail");
            }
        }
    }

    protected void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO user = service.update(FormUtil.toModel(UserDTO.class, req));
        if (user != null) {
            resp.sendRedirect(req.getContextPath() + "/home-page?message=Success");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home-page?message=Fail");
        }
    }
}
