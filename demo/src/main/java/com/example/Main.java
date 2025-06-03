package com.example;

import com.example.model.Plan;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Plan basic = new Plan("Basic", 9.99, "Basic access with limited features");
        Plan premium = new Plan("Premium", 19.99, "Full access with all features");
        Plan family = new Plan("Family", 29.99, "Family plan for up to 6 users");

        Plan[] plans = { basic, premium, family };

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SELECT YOUR PLAN ===");
        System.out.println();

        // Show options
        for (int i = 0; i < plans.length; i++) {
            System.out.println((i + 1) + ". " + plans[i]);
        }

        System.out.println();
        System.out.print("Choose an option (1-3): ");

        try {
            int option = scanner.nextInt();

            if (option >= 1 && option <= 3) {
                Plan selectedPlan = plans[option - 1];
                System.out.println();
                System.out.println("Excellent choice!");
                System.out.println("You have selected the plan: " + selectedPlan.getName());
                System.out.println("Price: $" + selectedPlan.getPrice());
                System.out.println("Description: " + selectedPlan.getDescription());
            } else {
                System.out.println("Invalid option. Please choose between 1 and 3.");
            }
        } catch (Exception e) {
            System.out.println("Error: Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }
}
