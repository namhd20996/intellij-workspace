package com.example.email;

import com.example.email.service.impl.SendEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoSendEmailApplication {

    @Autowired
    private SendEmailServiceImpl emailService;

    public static void main(String[] args) {
        SpringApplication.run(DemoSendEmailApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail(){
        emailService.sendEmail("namhdps25979@fpt.edu.vn","Gửi từ Duy Nam", "This is body");
    }

}
