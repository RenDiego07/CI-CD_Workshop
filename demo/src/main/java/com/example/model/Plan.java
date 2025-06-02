package com.example.model;

public class Plan {
    private String nombre;
    private double precio;
    private String descripcion;

    public Plan(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f - %s", nombre, precio, descripcion);
    }
}