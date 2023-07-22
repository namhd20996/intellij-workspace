package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    public List<User> userList() {
        return List.of(
                new User("sellsad", "123", "ADMIN", "Hoang Duy Nam", new Date(System.currentTimeMillis())),
                new User("faker", "123", "ADMIN", "Hoang Duy Nam", new Date(System.currentTimeMillis())),
                new User("despasito", "123", "USER", "Hoang Duy Nam", new Date(System.currentTimeMillis())),
                new User("tommy", "123", "ADMIN", "Hoang Duy Nam", new Date(System.currentTimeMillis())),
                new User("lambada", "123", "USER", "Hoang Duy Nam", new Date(System.currentTimeMillis()))
        );
    }

    @GetMapping("/if-unless")
    public ModelAndView if_unlessPage() {
        ModelAndView model = new ModelAndView("views/if-unless");
        model.addObject("users", userList());
        return model;
    }

    @GetMapping("/switch-case")
    public ModelAndView switchPage() {
        ModelAndView model = new ModelAndView("views/switch-case");
        model.addObject("user", new User("sellsad", "123", "ADMIN", "Hoang Duy Nam", new Date(System.currentTimeMillis())));
        return model;
    }

    @GetMapping("/submit-form")
    public ModelAndView doGetSubPage() {
        ModelAndView model = new ModelAndView("views/form-submit");
        model.addObject("employee", new User());
        return model;
    }

    @PostMapping("/submit-form")
    public ModelAndView subPage(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("views/form-submit");
        User user = new User("sellsad", "123", "ADMIN", "Hoang Duy Nam", new Date(System.currentTimeMillis()));
        req.getSession().setAttribute("employee", user);
        model.addObject("employee", user);
        return model;
    }

    @GetMapping("/get-parameter")
    public ModelAndView getParameter(@RequestParam("id") String id,
                                     @RequestParam("action") String action
    ) {
        ModelAndView model = new ModelAndView("result");
        System.out.println(id);
        System.out.println(action);
        return model;
    }
}
