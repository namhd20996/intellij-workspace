package com.example.dao;

import com.example.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    Book addBook(Book book);

    Book updateBook(Book book);

    Book deleteBook(Book book);

    List<Book> getBooks();

    List<Book> getBooks(String category);

    Book getBookById(String id);
}
