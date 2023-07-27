package com.example.loginsocial.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/api/home")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}
