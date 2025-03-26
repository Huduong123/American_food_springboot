package com.project.CKT3.ProjectCuoiKyT3.controller;


import com.project.CKT3.ProjectCuoiKyT3.entity.Product;
import com.project.CKT3.ProjectCuoiKyT3.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/menu")
    public String menu(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "menu";
    }
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
    @GetMapping("/store")
    public String store(){
        return "store";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}


