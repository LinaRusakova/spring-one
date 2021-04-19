package ru.geekbrains.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
public class MainController {

    @GetMapping()
    public String showIndex() {
        return "index";
    }


}
