package ru.geekbrains.spring.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
    @GetMapping
    public String showIndex(Model model) {
        return "index";
    }
}
