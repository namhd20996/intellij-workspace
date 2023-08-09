package com.example.dao.impl;

import com.example.dao.AbstractDao;
import com.example.dao.BookDAO;
import com.example.entity.Book;

import java.util.List;

public class BookDaoImpl extends AbstractDao<Book> implements BookDAO {
    @Override
    public Book addBook(Book book) {
        return create(book);
    }

    @Override
    public Book updateBook(Book book) {
        return update(book);
    }

    @Override
    public Book deleteBook(Book book) {
        return delete(book);
    }

    @Override
    public List<Book> getBooks() {
        return findAll(Book.class, false);
    }

    @Override
    public List<Book> getBooks(String category) {
        String hql = "SELECT o FROM Book o WHERE o.category = ?0";
        return findMany(Book.class, hql, category);
    }

    @Override
    public Book getBookById(String id) {
        String hql = "SELECT o FROM Book o WHERE o.id = ?0";
        return findOne(Book.class, hql, id);
    }
}
