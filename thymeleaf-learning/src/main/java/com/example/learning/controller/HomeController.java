package com.example.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("message", "Hello world!");
        return model;
    }

    @GetMapping("/demo-css")
    public String demoPage() {
        return "demo-css-js";
    }

    @GetMapping("/boostrap")
    public String boostrapPage() {
        return "add-boostrap";
    }
}
