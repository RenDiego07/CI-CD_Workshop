package com.example.model;

public class Plan {
    private String name;
    private double price;
    private String description;

    public Plan(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f - %s", name, price, description);
    }
}