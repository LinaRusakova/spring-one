package ru.geekbrains.spring.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.model.ProductModel;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductDao {
    private static SessionFactory factory;

    @PostConstruct
    public static void init() {
        try {
            forcePrepareFata();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        } finally {
//            shutdown();
//        }
    }

    public static void forcePrepareFata() {

        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }

        }
    }


    private static void shutdown() {
        factory.close();
    }


    public static List<ProductModel> findAll() {
        List<Product> productList = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            productList = session.createQuery("select p from Product p", Product.class).getResultList();
            session.getTransaction().commit();
        }
        List<ProductModel> resultList = new ArrayList<>();
        for (Product product : productList) {
            resultList.add(new ProductModel(product.getId(), product.getTitle(), product.getPrice()));
        }
        return resultList;
    }

    // поиск продукта по ID
    public static ProductModel findById(Long id) {
        Product fundedProduct = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            fundedProduct = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return (fundedProduct != null) ? new ProductModel(fundedProduct.getId(), fundedProduct.getTitle(), fundedProduct.getPrice()) : null;

    }


    public void saveOrUpdate(ProductModel productModel) {
        Product productToDB = new Product(productModel.getTitle(), productModel.getCost());
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(productToDB);
            session.getTransaction().commit();
        }
    }

    public static void deleteProduct(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product productToDelete = Optional.of(session.get(Product.class, id)).get();
            session.delete(productToDelete);
            session.getTransaction().commit();
        }
    }


    public void save(ProductModel product) {
        saveOrUpdate(product);
    }
}
