package com.example.lab.controller;

import com.example.lab.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpServletRequest req;

    private final HttpSession session;

    private final ServletContext context;

    private final ObjectMapper mapper;

    @GetMapping("/home-page")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("message", "Hello!..");
        return model;
    }

    @GetMapping("/student")
    public ModelAndView studentPage() throws IOException {
        ModelAndView model = new ModelAndView("student");
        Student student = mapper.readValue(
                new File("D:\\intellij-workspace\\java-six-lab-two\\src\\main\\resources\\static\\student.json"),
                Student.class
        );
        model.addObject("student", student);
        return model;
    }

    @GetMapping("/scope")
    public ModelAndView scopePage() {
        ModelAndView model = new ModelAndView("scope");
        model.addObject("a", "Model resp");
        req.setAttribute("b", "req resp");
        session.setAttribute("c", "session resp");
        context.setAttribute("d", "context resp");
        return model;
    }

    @GetMapping("/students")
    public ModelAndView studentsPage(@RequestParam("index") Optional<Integer> index) throws IOException {
        ModelAndView model = new ModelAndView("students");
        TypeReference<List<Student>> types = new TypeReference<>() {
        };
        List<Student> students = mapper.readValue(
                new File("D:\\intellij-workspace\\java-six-lab-two\\src\\main\\resources\\static\\students.json"),
                types
        );
        model.addObject("student", students.get(index.orElse(0)));
        return model;
    }

    @GetMapping("/operator")
    public ModelAndView operatorPage() {
        ModelAndView model = new ModelAndView("operator");
        model.addObject("x", 5);
        model.addObject("y", 8);
        return model;
    }

    @GetMapping("/attribute")
    public ModelAndView attributePage(@RequestParam("index") Optional<Integer> index) throws IOException {
        ModelAndView model = new ModelAndView("attribute");
        TypeReference<List<Student>> types = new TypeReference<>() {
        };
        List<Student> students = mapper.readValue(getFile(), types);
        model.addObject("students", students);
        model.addObject("student", students.get(index.orElse(0)));
        return model;
    }

    @GetMapping("/objects")
    public ModelAndView objectsPage(@RequestParam("index") Optional<Integer> index) throws IOException {
        ModelAndView model = new ModelAndView("objects");
        TypeReference<List<Student>> types = new TypeReference<>() {
        };
        List<Student> students = mapper.readValue(getFile(), types);
        model.addObject("students", students);
        model.addObject("now", new Date());
        return model;
    }

    private static File getFile() throws IOException {
        return new ClassPathResource("/static/students.json").getFile();
    }
}
