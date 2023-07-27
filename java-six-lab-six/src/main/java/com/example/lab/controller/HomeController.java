package com.example.lab.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HttpServletRequest request;

    @GetMapping("/index")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView("home/index");
        model.addObject("message", "This is home page");
        return model;
    }

    @GetMapping("/about")
    public ModelAndView aboutPage() {
        ModelAndView model = new ModelAndView("home/index");
        model.addObject("message", "This is about page");
        return model;
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView("home/index");
        if (!request.isUserInRole("ADMIN")) {
            return new ModelAndView("redirect:/auth/login/error");
        }
        model.addObject("message", "This is admin page");
        return model;
    }

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user")
    public ModelAndView userPage() {
        ModelAndView model = new ModelAndView("home/index");
        if (!(request.isUserInRole("ADMIN") || request.isUserInRole("USER"))) {
            return new ModelAndView("redirect:/auth/login/error");
        }
        model.addObject("message", "This is user page");
        return model;
    }
}
