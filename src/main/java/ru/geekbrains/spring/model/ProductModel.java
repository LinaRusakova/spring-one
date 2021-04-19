package ru.geekbrains.spring.model;

public class ProductModel {
    private Long id;
    private String title;
    private int cost;

    public ProductModel(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ProductModel(Long id, String name, int cost) {
        this.id = id;
        this.title = name;
        this.cost = cost;
    }

    public ProductModel() {
    }
}

