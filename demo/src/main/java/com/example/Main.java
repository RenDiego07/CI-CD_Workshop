package com.example;

import com.example.model.AdditionalFeature;
import com.example.model.Client;
import com.example.model.CostCalculation;
import com.example.model.Membership;
import com.example.model.Plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean confirmed = false;

        // 1) Planes disponibles
        Plan basic = new Plan("Basic", 40.00, "Basic access with limited features");
        Plan premium = new Plan("Premium", 70.00, "Full access with all features");
        Plan family = new Plan("Family", 200.00, "Family plan for up to 6 users");
        Plan[] plans = { basic, premium, family };

        // 2) Características adicionales disponibles
        List<AdditionalFeature> availableFeatures = List.of(
            new AdditionalFeature("Personal Training", 170),
            new AdditionalFeature("Group Classes",     30),
            new AdditionalFeature("Nutrition Plan",     20),
            new AdditionalFeature("Swimming Pool Access", 60)
        );

        // 3) ¿Cuántos miembros se inscriben juntos?
        System.out.print("How many members are signing up together? ");
        int groupSize;
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
                continue;
            }
            groupSize = scanner.nextInt();
            scanner.nextLine(); // limpiar salto de línea
            if (groupSize >= 1) break;
            System.out.println("The number of members must be at least 1.");
        }

        // 4) Leer nombre y edad de cada miembro
        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= groupSize; i++) {
            System.out.print("Enter name of member " + i + ": ");
            String name = scanner.nextLine();

            System.out.print("Enter age of member " + i + ": ");
            int age;
            while (true) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid age.");
                    scanner.next();
                    continue;
                }
                age = scanner.nextInt();
                scanner.nextLine(); // limpiar salto de línea
                if (age >= 0) break;
                System.out.println("Age cannot be negative.");
            }

            clients.add(new Client(i, name, age));
        }

        // 5) Mensaje informativo de descuento grupal
        if (groupSize >= 2) {
            System.out.println("If " + groupSize + " members sign up for the same plan, they will get a 10% group discount!");
        }

        Plan selectedPlan = null;
        List<AdditionalFeature> selectedFeatures = new ArrayList<>();

        // 6) Bucle principal
        while (!confirmed) {
            // 6.1) Mostrar planes disponibles
            System.out.println("\n=== SELECT YOUR PLAN ===");
            for (int i = 0; i < plans.length; i++) {
                System.out.printf("%d. %s%n", i + 1, plans[i]);
            }

            // 6.2) Leer opción de plan
            int option;
            while (true) {
                System.out.print("Choose a plan (1-3): ");
                if (!scanner.hasNextInt()) {
                    System.out.println(" Invalid input. Please enter a number between 1 and 3.");
                    scanner.next();
                    continue;
                }
                option = scanner.nextInt();
                scanner.nextLine();
                if (option >= 1 && option <= 3) break;
                System.out.println(" Option not available. Please select a number between 1 and 3.");
            }
            selectedPlan = plans[option - 1];

            // 6.3) Selección de características adicionales
            selectedFeatures.clear();
            System.out.println("\n=== ADDITIONAL FEATURES ===");
            for (int i = 0; i < availableFeatures.size(); i++) {
                System.out.printf("%d. %s ($%.2f)%n",
                                  i + 1,
                                  availableFeatures.get(i).getName(),
                                  availableFeatures.get(i).getCost());
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
                scanner.nextLine();
                if (featOption == 0) break;
                if (featOption >= 1 && featOption <= availableFeatures.size()) {
                    selectedFeatures.add(availableFeatures.get(featOption - 1));
                    System.out.println(" Feature added.");
                } else {
                    System.out.println(" Feature not available. Please select from the list.");
                }
            }

            // 6.4) Calcular y mostrar desglose INDIVIDUAL
            double groupSubtotal = 0.0;
            System.out.println("\n=== GROUP SUMMARY ===");
            System.out.println("Selected Plan: " + selectedPlan.getName());
            System.out.println("Additional Features (for all members):");
            for (AdditionalFeature af : selectedFeatures) {
                System.out.printf("- %s ($%.2f)%n", af.getName(), af.getCost());
            }

            System.out.println("\nMembers and individual costs:");
            for (Client c : clients) {
                // 6.4.1) Crear Membership para cada cliente
                Membership membership = new Membership(c, selectedPlan);
                for (AdditionalFeature f : selectedFeatures) {
                    membership.addAdditionalFeature(f);
                }

                // 6.4.2) Calcular base + extras usando CostCalculation
                double base = CostCalculation.calculateBaseCost(membership);
                double totalBeforeDiscount = CostCalculation.calculateTotalMembershipCost(membership);

                // 6.4.3) Aplicar descuentos especiales por monto
                double specialDiscount = 0;
                if (totalBeforeDiscount > 400) {
                    specialDiscount = 50;
                } else if (totalBeforeDiscount > 200) {
                    specialDiscount = 20;
                }
                double afterSpecial = totalBeforeDiscount - specialDiscount;

                // 6.4.4) Aplicar recargo del 15% si es plan Premium
                if (selectedPlan.getName().equalsIgnoreCase("Premium")) {
                    afterSpecial *= 1.15;
                }

                // 6.4.5) Mostrar desglose por miembro
                System.out.printf(
                    "- %s (age %s): Base+Extras $%.2f, Special Discount $%.2f, Subtotal $%.2f%n",
                    c.getName(),
                    c.getAge(),
                    totalBeforeDiscount,
                    specialDiscount,
                    afterSpecial
                );

                // 6.4.6) Acumular subtotal grupal
                groupSubtotal += afterSpecial;
            }

            // 6.5) Una vez terminado el bucle de todos los clientes, mostrar totales grupales
            System.out.printf("%nGroup Subtotal (sum of all individual subtotals): $%.2f%n", groupSubtotal);

            double groupDiscount = 0;
            if (groupSize >= 2) {
                groupDiscount = groupSubtotal * 0.10;
            }
            double groupTotal = groupSubtotal - groupDiscount;

            System.out.printf("Group Discount (10%%): -$%.2f%n", groupDiscount);
            System.out.printf("Final Group Total: $%.2f%n", groupTotal);

            // 6.6) Confirmar / Cancelar / Modificar (se ejecuta una sola vez por todo el grupo)
            System.out.println("\n=== CONFIRMATION ===");
            System.out.print("Do you want to confirm, cancel or modify? (confirm/cancel/modify): ");
            String response = scanner.next().toLowerCase();

            switch (response) {
                case "confirm":
                    confirmed = true;
                    System.out.println("Group membership confirmed! Thank you.");
                    break;
                case "cancel":
                    System.out.println("-1");
                    System.exit(-1);
                case "modify":
                    System.out.println("Returning to plan and features selection...");
                    // rompe switch y vuelve al while(!confirmed)
                    break;
                default:
                    System.out.println("Invalid option. Returning to selections...");
            }
        }

        scanner.close();
    }
}
