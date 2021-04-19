package ru.geekbrains.spring.bootone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.spring.bootone.model.ErrorPage;
import ru.geekbrains.spring.bootone.model.Product;
import ru.geekbrains.spring.bootone.services.ProductService;

import java.util.List;

@Controller
@RequestMapping()
public class MainController {

    private ProductService productService;
    private ErrorPage errorPage;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showIndex(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "index";
    }


}
