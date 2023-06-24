package com.example.modelattributelearning.controller;

import com.example.modelattributelearning.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/home-page")
    public ModelAndView getProduct(){
        return new ModelAndView("model-attribute-property/home");
    }

    @GetMapping("/home-page-two")
    public ModelAndView getProductTwo(){
        return new ModelAndView("model-attribute-property/home-two");
    }

    /*
    * Sử dụng  @ModelAttribute trên method nó sẽ chia sẻ dữ liệu cho tất cả các page
    * Page nào muốn sử dụng được chỉ cần lấy tên biến bên trong  @ModelAttribute("home") là có thể
    * lấy được value.
    * */
    @ModelAttribute("home")
    public Product product(){
        return new Product("Product one", 1000.0);
    }
}
