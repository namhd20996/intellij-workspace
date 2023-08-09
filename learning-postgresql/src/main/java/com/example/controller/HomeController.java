package com.example.controller;

import com.example.dto.BookDTO;
import com.example.service.BookService;
import com.example.util.FormUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(value = {
        "/home-page",
        "/book/v1/add",
        "/book/v1/find",
        "/book/v1/update",
        "/book/v1/delete",
        "/book/v1/edit"}, name = "ofHome")
public class HomeController extends HttpServlet {

    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/home-page" -> doGetHomePage(req, resp);
            case "/book/v1/edit" -> doGetEdit(req, resp);
        }
    }

    private void doGetHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = Optional.ofNullable(req.getParameter("message")).orElse("");
        req.setAttribute("message", message);
        req.setAttribute("books", bookService.getBooks());
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

    private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("book", bookService.getBookById(req.getParameter("id")));
        req.setAttribute("books", bookService.getBooks());
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/book/v1/add" -> doPostAdd(req, resp);
            case "/book/v1/update" -> doPostUpdate(req, resp);
            case "/book/v1/delete" -> doPostDelete(req, resp);
            case "/book/v1/find" -> doPostBooksByCategory(req, resp);
        }
    }

    private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDTO request = FormUtil.toModel(BookDTO.class, req);
        BookDTO book = bookService.addBook(request);
        if (book != null) {
            resp.sendRedirect(req.getContextPath() + "/home-page?message=add success.");
        }

        if (book == null) {
            req.setAttribute("message", "add fail id duplicate");
            req.setAttribute("book", request);
            req.setAttribute("books", bookService.getBooks());
            getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
        }
    }

    private void doPostBooksByCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("categoryFind");
        List<BookDTO> books = bookService.getBooks(category);
        if (books.isEmpty()) {
            req.setAttribute("books", bookService.getBooks());
        }
        if (!books.isEmpty()) {
            req.setAttribute("books", books);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

    private void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDTO request = FormUtil.toModel(BookDTO.class, req);
        BookDTO book = bookService.updateBook(request);
        if (book != null) {
            req.setAttribute("message", "Update book success.");
            resp.sendRedirect(req.getContextPath() + "/home-page?message=update success.");
        }
        if (book == null) {
            req.setAttribute("book", request);
            req.setAttribute("message", "update book fail.");
            req.setAttribute("books", bookService.getBooks());
            getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
        }
    }

    private void doPostDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            bookService.deleteBook(id);
        }
        req.setAttribute("books", bookService.getBooks());
        getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

}
