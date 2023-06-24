package com.example.springsecuritylearningapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeResoure {

    @GetMapping("/hello/v1")
    public ResponseEntity<String> helloApi() {
        return new ResponseEntity<>("Hello API!", HttpStatus.OK);
    }

    @GetMapping("/good-bye/v1")
    public ResponseEntity<String> goodByeApi() {
        return new ResponseEntity<>("Good Bye API!", HttpStatus.OK);
    }
}
