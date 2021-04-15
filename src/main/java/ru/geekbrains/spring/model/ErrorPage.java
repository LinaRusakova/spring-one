package ru.geekbrains.spring.model;

public class ErrorPage {
    private String message;

    public String getMessage() {
        return message;
    }

    public ErrorPage(String message) {
        this.message = message;
    }
}
