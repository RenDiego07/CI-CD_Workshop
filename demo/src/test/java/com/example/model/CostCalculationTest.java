package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CostCalculationTest {

    @Test
    public void testCalculateBaseCost() {
        Client client = new Client(1, "Maria", 28);
        Plan plan = new Plan("Basic", 50.0, "Basic plan");
        Membership m = new Membership(client, plan);

        double baseCost = CostCalculation.calculateBaseCost(m);
        assertEquals(50.0, baseCost, 0.01);
    }

    @Test
    public void testCalculateAdditionalFeaturesCost() {
        Client client = new Client(2, "Luis", 35);
        Plan plan = new Plan("Family", 200.0, "Family plan");
        Membership m = new Membership(client, plan);

        m.addAdditionalFeature(new AdditionalFeature("Gym Bag", 25.0));
        m.addAdditionalFeature(new AdditionalFeature("Nutrition Plan", 30.0));

        double extrasCost = CostCalculation.calculateAdditionalFeaturesCost(m);
        assertEquals(55.0, extrasCost, 0.01);
    }

    @Test
    public void testCalculateTotalMembershipCost() {
        Client client = new Client(3, "Ana", 22);
        Plan plan = new Plan("Premium", 70.0, "Premium plan");
        Membership m = new Membership(client, plan);

        m.addAdditionalFeature(new AdditionalFeature("Trainer", 100.0));
        m.addAdditionalFeature(new AdditionalFeature("Pool", 50.0));
        double total = CostCalculation.calculateTotalMembershipCost(m);
        assertEquals(220.0, total, 0.01);
    }
}
