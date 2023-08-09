package com.example.mapper;

import com.example.dto.BookDTO;
import com.example.entity.Book;
import com.google.common.base.Function;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class BookDTOMapper implements Function<Book, BookDTO> {

    private ModelMapper mapper = new ModelMapper();

    public static BookDTOMapper getInstance() {
        BookDTOMapper b = null;
        if (b == null) {
            b = new BookDTOMapper();
        }
        return b;
    }


    @Override
    public BookDTO apply(Book request) {
        return Optional.of(request)
                .map(b -> mapper.map(b, BookDTO.class))
                .orElse(null);
    }

}
