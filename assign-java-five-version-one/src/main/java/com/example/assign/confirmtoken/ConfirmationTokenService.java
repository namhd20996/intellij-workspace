package com.example.assign.confirmtoken;

public interface ConfirmationTokenService {

    ConfirmationTokenDTO findByToken(String token);

    void setConfirmeAt(String token);

    void saveConfirmationToken(ConfirmationToken token);
}
