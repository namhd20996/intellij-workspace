package com.example.service.impl;

import com.example.dao.BookDAO;
import com.example.dto.BookDTO;
import com.example.entity.User;
import com.example.mapper.BookDTOMapper;
import com.example.service.BookService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    @Inject
    private BookDAO bookDAO;

    @Override
    public BookDTO addBook(BookDTO request) {
        User book = bookDAO.getBookById(request.getId());
        if (book != null) {
            throw new RuntimeException("id already!.");
        }
        User entity = bookDAO.addBook(
                new User(
                        request.getId(),
                        request.getName(),
                        request.getPrice(),
                        request.getYear(),
                        request.getCategory()
                )
        );
        return BookDTOMapper.getInstance().apply(entity);
    }

    @Override
    public BookDTO updateBook(BookDTO request) {
        User bookOld = bookDAO.getBookById(request.getId());
        if (bookOld == null) {
           throw new RuntimeException("");
        }

        if (bookOld.getName() == null || !bookOld.getName().equals(request.getName())) {
            bookOld.setName(request.getName());
        }

        if (bookOld.getPrice() == null || !bookOld.getPrice().equals(request.getPrice())) {
            bookOld.setPrice(request.getPrice());
        }

        if (bookOld.getYear() == null || !bookOld.getYear().equals(request.getYear())) {
            bookOld.setYear(request.getYear());
        }

        if (bookOld.getCategory() == null || !bookOld.getCategory().equals(request.getCategory())) {
            bookOld.setCategory(request.getCategory());
        }
        User entity = bookDAO.updateBook(bookOld);
        return BookDTOMapper.getInstance().apply(entity);
    }

    @Override
    public void deleteBook(String id) {
        User entity = bookDAO.getBookById(id);
        bookDAO.deleteBook(entity);
    }

    @Override
    public List<BookDTO> getBooks() {
        return bookDAO.getBooks()
                .stream()
                .map(b -> BookDTOMapper.getInstance().apply(b))
                .toList();
    }

    @Override
    public List<BookDTO> getBooks(String category) {
        return bookDAO.getBooks(category)
                .stream()
                .map(b -> BookDTOMapper.getInstance().apply(b))
                .toList();
    }

    @Override
    public BookDTO getBookById(String id) {
        return Optional.of(bookDAO.getBookById(id))
                .map(b -> BookDTOMapper.getInstance().apply(b))
                .orElseThrow(() -> new IllegalArgumentException("book find by id: " + id + " not found!."));
    }
}
