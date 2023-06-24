package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home-page")
    public String homePage() {
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/index";
    }

    @GetMapping("/manager")
    public String managerPage() {
        return "manager/index";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user/index";
    }

}
