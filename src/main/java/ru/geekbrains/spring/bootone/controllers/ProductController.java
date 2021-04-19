package ru.geekbrains.spring.bootone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.bootone.model.Product;
import ru.geekbrains.spring.bootone.model.ErrorPage;
import ru.geekbrains.spring.bootone.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ErrorPage errorPage;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public String showAllProducts(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "products";
    }

    @PostMapping("/find")
    public String showProductInfo(@RequestParam(name = "id") Long id, Model model) {
        Optional<Product> product = productService.findOneById(id);
        product.ifPresent(value -> model.addAttribute("product", value));
        return "product_info";
    }

    @GetMapping("/find")
    public String showFinderForm(Model model) {
        return "find_product_form";
    }


    @GetMapping("/create")
    public String showCreatorForm(Model model) {
        return "add_product_form";
    }

    @GetMapping("/wrong")
    public String showWrongPage(Model model) {
        return "wrong";
    }

    @PostMapping("/create")
    public String createNewProduct(@RequestParam Long id, @RequestParam(defaultValue = "_notInput") String title, @RequestParam(defaultValue = "null") Double cost, Model model) {
        if (title.equals("_notInput")) {
            String message = "Вы не указали значение для поля: \"Название продукта\".";
            ErrorPage errorPage = new ErrorPage(message);
            model.addAttribute("error", errorPage);
            return "wrong";
        } else {
            Product product = new Product(id, title, cost);
            productService.save(product);
            return "redirect:/products/all";
        }

    }
}
