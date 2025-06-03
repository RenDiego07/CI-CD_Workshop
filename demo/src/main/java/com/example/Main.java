package com.example;

import com.example.model.Client;
import com.example.model.CostCalculation;
import com.example.model.Plan;
import com.example.model.Membership;
import com.example.model.AdditionalFeature;

public class Main {
    public static void main(String[] args) {
        Client cliente = new Client(1,"Juan Pérez", 80);

        Plan planBasico = new Plan(2,"Básico", 50.0);

        Membership membresia = new Membership(cliente, planBasico);

        AdditionalFeature pt = new AdditionalFeature("Personal Training", 30.0);
        AdditionalFeature grupo = new AdditionalFeature("Clases Grupales", 35.0);
        membresia.additionalFeature(pt);
        membresia.additionalFeature(grupo);

        double costoBase       = CostCalculation.calculateBaseCost(membresia);
        double costoAdicional  = CostCalculation.calculateAdditionalFeaturesCost(membresia);
        double costoTotal      = CostCalculation.calculateTotalMembershipCost(membresia);

        System.out.println("Costo base del plan: $" + costoBase);
        System.out.println("Costo características adicionales: $" + costoAdicional);
        System.out.println("Costo total de membresía: $" + costoTotal);
    }
}
