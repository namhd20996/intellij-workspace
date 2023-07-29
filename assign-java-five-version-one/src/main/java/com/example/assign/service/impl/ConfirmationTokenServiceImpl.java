package com.example.assign.service.impl;

import com.example.assign.converter.ConfirmationTokenConverter;
import com.example.assign.dto.ConfirmationTokenDTO;
import com.example.assign.entity.ConfirmationToken;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.repo.ConfirmationTokenRepo;
import com.example.assign.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    private final ConfirmationTokenConverter confirmationTokenConverter;

    @Override
    public ConfirmationTokenDTO findByToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepo.findByToken(token)
                .orElseThrow(() -> new ApiRequestException("Token not found!.."));
        return confirmationTokenConverter.apply(confirmationToken);
    }

    @Override
    public int setConfirmeAt(String token) {
        return confirmationTokenRepo.updateConfirmeAt(token, LocalDateTime.now());
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }
}
