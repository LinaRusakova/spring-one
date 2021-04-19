package ru.geekbrains.spring.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao {
private static SessionFactory factory;


    public static void init() {
        forcePrepareFata();
    }

    public static void forcePrepareFata() {
        SessionFactory factory = new Configuration()
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
            if (session!=null) {
                session.close();
            }

        }
    }

    public static void start(String[] args) {
        try {
            init();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    private static void shutdown() {
        factory.close();
    }



    public static List<Product> findAll() {
        List<Product> productList = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            productList = session.createSQLQuery("select from product_db").getResultList();
            Product simpleItem = session.get(Product.class, 1);
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
        return productList;
    }

    // поиск всех продуктов в базе
    public static void fillAllProducts() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            for (int i = 0; i < 20; i++) {

            }
            Product simpleItem = session.get(Product.class, 1);
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
    }

    // поиск продукта по ID
    public Product findById(Long id) {
        Product findedProduct;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            findedProduct = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return findedProduct;
    }

    // удаление продукта по ID
    public void deleteById(Long id) {
    }

    public Product saveOrUpdate(Product product) {
        Product productInDB = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            if (product.getId() != null) {
                productInDB = session.get(Product.class, product.getId());
                if (product.getId() == productInDB.getId()) {
                    productInDB.setTitle(product.getTitle());
                    productInDB.setPrice(product.getPrice());
                } else {
                    session.save(product);
                }

                session.getTransaction().commit();
            }
            return productInDB;
        }
    }

    public static void updateProduct(String nameProduct, int price) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product alterItem = new Product("Cacao", 100);
            alterItem.setPrice(34);
            System.out.println(alterItem);
            session.getTransaction().commit();
        }
    }


}
