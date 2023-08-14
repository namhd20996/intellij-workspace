package com.example.dao.impl;

import com.example.dao.AbstractDao;
import com.example.dao.BookDAO;
import com.example.entity.User;

import java.util.List;

public class BookDaoImpl extends AbstractDao<User> implements BookDAO {
    @Override
    public User addBook(User book) {
        return create(book);
    }

    @Override
    public User updateBook(User book) {
        return update(book);
    }

    @Override
    public User deleteBook(User book) {
        return delete(book);
    }

    @Override
    public List<User> getBooks() {
        return findAll(User.class, false);
    }

    @Override
    public List<User> getBooks(String category) {
        String hql = "SELECT o FROM Book o WHERE o.category = ?0";
        return findMany(User.class, hql, category);
    }

    @Override
    public User getBookById(String id) {
        String hql = "SELECT o FROM Book o WHERE o.id = ?0";
        return findOne(User.class, hql, id);
    }
}
