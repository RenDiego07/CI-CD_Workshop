package com.example.model;

public class CostCalculation {
    public static double calculateBaseCost(Membership membership) {
        if (membership.getPlan() != null) {
            return membership.getPlan().getPrice();
        }
        return 0.0;
    }

    public static double calculateAdditionalFeaturesCost(Membership membership) {
        double total = 0.0;
        for (AdditionalFeature feature : membership.getAdditionalFeatures()) {
            total += feature.getCost();
        }
        return total;
    }

    public static double calculateTotalMembershipCost(Membership membership) {
        double baseCost = calculateBaseCost(membership);
        double additionalCost = calculateAdditionalFeaturesCost(membership);
        return baseCost + additionalCost;
    }
}
