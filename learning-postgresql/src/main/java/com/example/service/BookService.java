package com.example.service;

import com.example.dto.BookDTO;
import com.example.entity.Book;

import java.util.List;

public interface BookService {

    BookDTO addBook(BookDTO book);

    BookDTO updateBook(BookDTO book);

    BookDTO getBookById(String id);

    void deleteBook(String id);

    List<BookDTO> getBooks();

    List<BookDTO> getBooks(String category);

}
