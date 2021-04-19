package ru.geekbrains.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.spring.model.ErrorPage;
import ru.geekbrains.spring.model.ProductModel;
import ru.geekbrains.spring.repositories.ProductDao;
import ru.geekbrains.spring.services.ProductService;

import java.util.List;

@Controller
@RequestMapping()
public class MainController {

    private ProductService productService;
    private ErrorPage errorPage;


    @GetMapping()
    public String showIndex() {
        return "index";
    }


}
