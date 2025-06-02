package com.example.model;

import java.util.UUID;

public class AdditionalFeautre {
    private UUID id;
    private String name;
    private double cost;

    public AdditionalFeautre(String name, double cost) {
        id = UUID.randomUUID();
        this.name = name;
        this.cost = cost;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
