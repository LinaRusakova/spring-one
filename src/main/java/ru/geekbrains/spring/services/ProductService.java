package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.Product;
import ru.geekbrains.spring.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findOneById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void changeCost(Long id, Character vector) {
        Optional<Product> product = findOneById(id);
        Product tempProduct = product.get();
        if (vector.equals(Character.valueOf('+')) && (tempProduct.getCost() + 1) <= 100) {
            tempProduct.setCost(tempProduct.getCost() + 1);
            productRepository.changeProduct(tempProduct);
        } else if (vector.equals(Character.valueOf('-')) && (tempProduct.getCost() - 1) >= 0) {
            tempProduct.setCost(tempProduct.getCost() - 1);
            productRepository.changeProduct(tempProduct);
        }

    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
