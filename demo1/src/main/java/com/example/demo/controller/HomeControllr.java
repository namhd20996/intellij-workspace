package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllr {

    @GetMapping("/home-page")
    public String homePage() {
        return "home";
    }
}
