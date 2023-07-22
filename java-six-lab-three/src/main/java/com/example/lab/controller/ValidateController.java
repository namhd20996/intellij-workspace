package com.example.lab.controller;

import com.example.lab.model.StudentTwo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ValidateController {

    @GetMapping("/form-validate")
    public ModelAndView formGet(@ModelAttribute("student") StudentTwo studentTwo) {
        return new ModelAndView("validate-form");
    }

    @PostMapping("/form-validate")
    public ModelAndView formPost(@Validated @ModelAttribute("student") StudentTwo studentTwo, Errors errors) {
        ModelAndView model = new ModelAndView("success");
        if (errors.hasErrors()) {
            model.addObject("message", "Vui lòng kiểm tra các lỗi sau");
            model.setViewName("validate-form");
        }
        return model;
    }
}
