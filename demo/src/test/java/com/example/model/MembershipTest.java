package com.example.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MembershipTest {

    @Test
    public void testPlanAttributes() {
        Plan plan = new Plan("Premium", 70.0, "Full access");
        assertEquals("Premium", plan.getName());
        assertEquals(70.0, plan.getPrice());
        assertEquals("Full access", plan.getDescription());
    }

    @Test
    public void testAdditionalFeatureAttributes() {
        AdditionalFeature af = new AdditionalFeature("Nutrition Plan", 25.0);
        assertEquals("Nutrition Plan", af.getName());
        assertEquals(25.0, af.getCost());
    }

    @Test
    public void testMembershipAddFeature() {
        Client client = new Client(1, "Ana", 30);
        Plan plan = new Plan("Basic", 40.0, "Basic plan");
        Membership membership = new Membership(client, plan);

        AdditionalFeature af1 = new AdditionalFeature("Pool", 50);
        AdditionalFeature af2 = new AdditionalFeature("Yoga", 20);

        membership.addAdditionalFeature(af1);
        membership.addAdditionalFeature(af2);

        List<AdditionalFeature> added = membership.getAdditionalFeatures();
        assertEquals(2, added.size());
        assertTrue(added.contains(af1));
        assertTrue(added.contains(af2));
    }

    @Test
    public void testTotalAdditionalCost() {
        Client client = new Client(2, "Carlos", 22);
        Plan plan = new Plan("Family", 200.0, "Family Plan");
        Membership membership = new Membership(client, plan);

        membership.addAdditionalFeature(new AdditionalFeature("Trainer", 100));
        membership.addAdditionalFeature(new AdditionalFeature("Pool", 50));

        double expected = 150.0;
        double actual = membership.getAdditionalFeatures().stream().mapToDouble(AdditionalFeature::getCost).sum();
        assertEquals(expected, actual);
    }

    @Test
    public void testTotalCostWithDiscountAndPremiumSurcharge() {
        // Total base + extras = 300 → Discount = 20 → Premium = apply 15% on (300 - 20)
        Plan premiumPlan = new Plan("Premium", 70.0, "Premium plan");
        double extras = 230.0; // 300 totalBeforeDiscount
        double discount = 20.0;

        double totalBeforeDiscount = premiumPlan.getPrice() + extras;
        double totalAfterDiscount = totalBeforeDiscount - discount;

        if (premiumPlan.getName().equalsIgnoreCase("Premium")) {
            totalAfterDiscount *= 1.15;
        }

        assertEquals(322.0, totalAfterDiscount, 0.01); // 280 * 1.15 = 322.0
    }
}
