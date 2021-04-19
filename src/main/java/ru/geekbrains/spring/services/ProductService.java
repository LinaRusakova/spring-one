package ru.geekbrains.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.ProductModel;

import ru.geekbrains.spring.repositories.ProductDao;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    private final ProductDao productRepoDB;

    @Autowired
    public ProductService(ProductDao productRepoDB) {
        this.productRepoDB = productRepoDB;
    }

    public List<ProductModel> findAll() {
        List<ProductModel> resultList = productRepoDB.findAll();
        return resultList;
    }

    public ProductModel findById(Long id) {
        return productRepoDB.findById(id);
    }

    public void save(ProductModel product) {
        productRepoDB.saveOrUpdate(product);
    }
}
