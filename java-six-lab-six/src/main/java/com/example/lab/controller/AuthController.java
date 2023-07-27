package com.example.lab.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final HttpServletRequest request;

    @GetMapping("/login/login-page")
    public ModelAndView loginPage(@RequestParam(value = "message", required = false) String message) {
        ModelAndView modelAndView = new ModelAndView("auth/login");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    //    @PreAuthorize("isAuthenticated()")
    @GetMapping("/authenticated")
    public ModelAndView authenticatedPage() {
        ModelAndView model = new ModelAndView("home/index");
        if (request.getRemoteUser() == null) {
            return new ModelAndView("redirect:/auth/login/login-page");
        }
        model.addObject("message", "This is user page");
        return model;
    }


    @GetMapping("/login/error")
    public ModelAndView errorPage() {
        ModelAndView model = new ModelAndView("auth/login");
        model.addObject("message", "You not permission");
        return model;
    }
}
