package com.example.assign.confirmtoken;

import com.example.assign.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    private final ConfirmationTokenMapper confirmationTokenConverter;

    @Override
    public ConfirmationTokenDTO findByToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepo.findByToken(token)
                .orElseThrow(() -> new ApiRequestException("Token not found!.."));
        return confirmationTokenConverter.apply(confirmationToken);
    }

    @Override
    public void setConfirmeAt(String token) {
        confirmationTokenRepo.updateConfirmAt(token, LocalDateTime.now());
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }
}
