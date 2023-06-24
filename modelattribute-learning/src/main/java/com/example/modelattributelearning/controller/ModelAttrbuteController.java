package com.example.modelattributelearning.controller;

import com.example.modelattributelearning.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModelAttrbuteController {

    @GetMapping("/home-page-attr")
    public ModelAndView getProductAttr() {
        return new ModelAndView("model-attribute-attr/home");
    }

    /*
     * Sử dụng  @ModelAttribute bên trong như là một Attribute thì nó giống như Bean bên JSP
     * nó sẽ map các field của Object với các name ở Page và khi sử dụng kèm theo @ModelAttribute("product")
     * tên biến bên trong thì khi sử dụng bên Page chỉ cần lấy tên biến là được nó có tác dụng giống như đã có
     * add một Object trả về Page với tên là tên biến bên trong. Và nó chỉ sử dụng được ở POST.
     * */
    @PostMapping("/home-page-attr")
    public ModelAndView product(@ModelAttribute("product") Product product) {
        return new ModelAndView("model-attribute-attr/home-two");
    }
}
