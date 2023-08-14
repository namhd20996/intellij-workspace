package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface BookDAO {

    User addBook(User book);

    User updateBook(User book);

    User deleteBook(User book);

    List<User> getBooks();

    List<User> getBooks(String category);

    User getBookById(String id);
}
