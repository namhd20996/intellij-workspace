package com.example.lab.controller;

import com.example.lab.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/form-submit")
    public ModelAndView formPage() {
        ModelAndView model = new ModelAndView("form");
        Student student = new Student();
        student.setAverage(10.0);
        student.setCountry("vn");
        student.setGender(true);
        model.addObject("student", student);
        return model;
    }

    @GetMapping("/frags")
    public String fragsPage() {
        return "page";
    }

    @GetMapping("/home-page")
    public String homePage() {
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "home/about";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "home/index";
    }

    @PostMapping("/form-submit")
    public ModelAndView formPost(@ModelAttribute("student") Student student) {
        return new ModelAndView("success");
    }
}
