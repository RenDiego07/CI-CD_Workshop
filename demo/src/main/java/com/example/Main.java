package com.example;

import com.example.model.Plan;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Plan basic = new Plan("Basic", 9.99, "Acceso básico con funciones limitadas");
        Plan premium = new Plan("Premium", 19.99, "Acceso completo con todas las funciones");
        Plan family = new Plan("Family", 29.99, "Plan familiar para hasta 6 usuarios");

        Plan[] planes = { basic, premium, family };

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SELECCIONA TU PLAN ===");
        System.out.println();

        // Mostrar opciones
        for (int i = 0; i < planes.length; i++) {
            System.out.println((i + 1) + ". " + planes[i]);
        }

        System.out.println();
        System.out.print("Elige una opción (1-3): ");

        try {
            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 3) {
                Plan planSeleccionado = planes[opcion - 1];
                System.out.println();
                System.out.println("¡Excelente elección!");
                System.out.println("Has seleccionado el plan: " + planSeleccionado.getNombre());
                System.out.println("Precio: $" + planSeleccionado.getPrecio());
                System.out.println("Descripción: " + planSeleccionado.getDescripcion());
            } else {
                System.out.println("Opción inválida. Por favor elige entre 1 y 3.");
            }
        } catch (Exception e) {
            System.out.println("Error: Por favor ingresa un número válido.");
        } finally {
            scanner.close();
        }
    }
}