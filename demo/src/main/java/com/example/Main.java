package com.example;

import com.example.model.AdditionalFeature;
import com.example.model.Client;
import com.example.model.Membership;
import com.example.model.Plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean confirmed = false;

        // Planes disponibles
        Plan basic = new Plan("Basic", 40.00, "Basic access with limited features");
        Plan premium = new Plan("Premium", 70.00, "Full access with all features");
        Plan family = new Plan("Family", 200.00, "Family plan for up to 6 users");
        Plan[] plans = { basic, premium, family };

        // Características adicionales disponibles
        List<AdditionalFeature> availableFeatures = List.of(
                new AdditionalFeature("Personal Training", 170),
                new AdditionalFeature("Group Classes", 30),
                new AdditionalFeature("Nutrition Plan", 20),
                new AdditionalFeature("Swimming Pool Access", 60)
        );

        Client client = new Client(1, "John Doe", 25);
        Plan selectedPlan = null;
        List<AdditionalFeature> selectedFeatures = new ArrayList<>();

        while (!confirmed) {
            // Mostrar planes
            System.out.println("=== SELECT YOUR PLAN ===");
            for (int i = 0; i < plans.length; i++) {
                System.out.printf("%d. %s%n", i + 1, plans[i]);
            }

            // Validar selección
            int option = -1;
            while (true) {
                System.out.print("Choose a plan (1-3): ");
                if (!scanner.hasNextInt()) {
                    System.out.println(" Invalid input. Please enter a number between 1 and 3.");
                    scanner.next();
                    continue;
                }

                option = scanner.nextInt();
                if (option >= 1 && option <= 3) break;
                else System.out.println(" Option not available. Please select a number between 1 and 3.");
            }

            selectedPlan = plans[option - 1];
            selectedFeatures.clear();

            // Selección de features
            System.out.println("\n=== ADDITIONAL FEATURES ===");
            for (int i = 0; i < availableFeatures.size(); i++) {
                System.out.printf("%d. %s ($%.2f)%n", i + 1, availableFeatures.get(i).getName(), availableFeatures.get(i).getCost());
            }

            System.out.println("Enter the number of the feature to add (0 to finish):");
            while (true) {
                System.out.print("Option: ");
                if (!scanner.hasNextInt()) {
                    System.out.println(" Invalid input. Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                int featOption = scanner.nextInt();
                if (featOption == 0) break;
                if (featOption >= 1 && featOption <= availableFeatures.size()) {
                    selectedFeatures.add(availableFeatures.get(featOption - 1));
                    System.out.println(" Feature added.");
                } else {
                    System.out.println(" Feature not available. Please select from the list.");
                }
            }

            // Crear membresía y calcular
            Membership membership = new Membership(client, selectedPlan);
            selectedFeatures.forEach(membership::addAdditionalFeature);

            double base = selectedPlan.getPrice();
            double extras = selectedFeatures.stream().mapToDouble(AdditionalFeature::getCost).sum();
            double totalBeforeDiscount = base + extras;

            double discount = 0;
            if (totalBeforeDiscount > 400) discount = 50;
            else if (totalBeforeDiscount > 200) discount = 20;

            double total = totalBeforeDiscount - discount;
            if (selectedPlan.getName().equalsIgnoreCase("Premium")) {
                total *= 1.15;
            }

            // Mostrar resumen
            System.out.println("\n=== CONFIRMATION ===");
            System.out.println("Selected Plan: " + selectedPlan.getName());
            System.out.printf("Base Price: $%.2f%n", base);
            System.out.println("Additional Features: ");
            for (AdditionalFeature af : selectedFeatures) {
                System.out.printf("- %s ($%.2f)%n", af.getName(), af.getCost());
            }
            System.out.printf("Total Discount: -$%.2f%n", discount);
            System.out.printf("Total Cost (after adjustments): $%.2f%n", total);

            // Confirmar, cancelar o modificar
            System.out.print("Do you want to confirm, cancel or modify? (confirm/cancel/modify): ");
            String response = scanner.next().toLowerCase();

            switch (response) {
                case "confirm":
                    confirmed = true;
                    System.out.println(" Membership confirmed!");
                    break;
                case "cancel":
                    System.out.println(" -1. \n Membership canceled. Exiting.");
                    System.exit(0);
                case "modify":
                    System.out.println(" Let's modify your selections...");
                    break;
                default:
                    System.out.println(" Invalid option. Returning to selections.");
            }
        }

        scanner.close();
    }
}
