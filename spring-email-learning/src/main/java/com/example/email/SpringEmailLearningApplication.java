package com.example.email;

import com.example.email.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class SpringEmailLearningApplication {

    @Autowired
    private MailServiceImpl mailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringEmailLearningApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
//    @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    public void sendEmail(){
        mailService.push("namhdps25979@fpt.edu.vn","Gửi từ Duy Nam", "This 1 is body");
        mailService.run();
    }
}
