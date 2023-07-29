package com.example.assign.service;

import com.example.assign.dto.ConfirmationTokenDTO;
import com.example.assign.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    ConfirmationTokenDTO findByToken(String token);

    int setConfirmeAt(String token);

    void saveConfirmationToken(ConfirmationToken token);
}
