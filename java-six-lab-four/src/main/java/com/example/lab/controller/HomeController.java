package com.example.lab.controller;

import com.example.lab.model.Student;
import com.example.lab.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class HomeController {

    private final StudentRepo repo;

    @GetMapping("/home-page")
    private ModelAndView homePage() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("student", new Student());
        model.addObject("students", repo.getAll());
        return model;
    }

    @PostMapping("/create")
    public ModelAndView createStudent(@ModelAttribute Student student) {
        ModelAndView model = new ModelAndView("redirect:/student/home-page");
        repo.create(student);
        model.addObject("student", student);
        return model;
    }

    @PostMapping("/update/{key}")
    public ModelAndView updateStudent(@ModelAttribute Student student, @PathVariable("key") String key) {
        ModelAndView model = new ModelAndView("redirect:/student/home-page");
        repo.update(key, student);
        model.addObject("student", student);
        return model;
    }

    @GetMapping("/delete/{key}")
    public ModelAndView deleteStudent(@PathVariable("key") String key) {
        ModelAndView model = new ModelAndView("redirect:/student/home-page");
        repo.delete(key);
        return model;
    }

    @GetMapping("/edit/{key}")
    public ModelAndView editStudent(@PathVariable("key") String key) {
        ModelAndView model = new ModelAndView("index");
        model.addObject("key", key);
        model.addObject("students", repo.getAll());
        model.addObject("student", repo.findOneById(key));
        return model;
    }

}
