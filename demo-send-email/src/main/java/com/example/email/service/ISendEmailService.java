package com.example.email.service;

public interface ISendEmailService {

    void sendEmail(String toEmail, String subject, String body);
}
