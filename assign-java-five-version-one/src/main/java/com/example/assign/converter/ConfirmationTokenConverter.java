package com.example.assign.converter;

import com.example.assign.dto.ConfirmationTokenDTO;
import com.example.assign.entity.ConfirmationToken;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ConfirmationTokenConverter implements
        Function<ConfirmationToken, ConfirmationTokenDTO> {

    private final ModelMapper mapper;

    @Override
    public ConfirmationTokenDTO apply(ConfirmationToken confirmationToken) {
        return mapper.map(confirmationToken, ConfirmationTokenDTO.class);
    }

    public ConfirmationToken apply(ConfirmationTokenDTO confirmationToken) {
        return mapper.map(confirmationToken, ConfirmationToken.class);
    }
}
