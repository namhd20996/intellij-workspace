package com.example.spring.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeResoure {

    @GetMapping("/api-one")
    public ResponseEntity<String> apiOne() {
        return new ResponseEntity<>("API One", HttpStatus.OK);
    }

    @GetMapping("/api-two")
    public ResponseEntity<String> apiTwo() {
        return new ResponseEntity<>("API Two", HttpStatus.OK);
    }

    @GetMapping("/api-three")
    public ResponseEntity<String> apiThree() {
        return new ResponseEntity<>("API Three", HttpStatus.OK);
    }
}
