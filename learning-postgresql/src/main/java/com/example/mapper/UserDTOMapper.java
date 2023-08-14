package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.google.common.base.Function;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class BookDTOMapper implements Function<User, UserDTO> {

    private ModelMapper mapper = new ModelMapper();

    public static BookDTOMapper getInstance() {
        BookDTOMapper b = null;
        if (b == null) {
            b = new BookDTOMapper();
        }
        return b;
    }


    @Override
    public UserDTO apply(User request) {
        return Optional.of(request)
                .map(b -> mapper.map(b, UserDTO.class))
                .orElse(null);
    }

}
