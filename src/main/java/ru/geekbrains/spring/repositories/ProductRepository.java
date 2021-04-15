package ru.geekbrains.spring.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk", 35.50),
                new Product(2L, "Chocolate", 44.10),
                new Product(3L, "Coffee", 99.99),
                new Product(4L, "Beacon", 124.34),
                new Product(5L, "Tea", 84.22)
        ));
    }

    public List<Product> findAll() {
        return productList;
    }

    public Optional<Product> findOneById(Long id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public void save(Product product) {
        productList.add(product);
    }
}