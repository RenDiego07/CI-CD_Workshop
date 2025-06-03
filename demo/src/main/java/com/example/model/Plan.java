package com.example.model;

import java.util.UUID;

public class Plan {
    private int id;
    private String name;
    private double price;

    public Plan(int id,String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

}
