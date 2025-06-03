package com.example.model;
import java.util.ArrayList;
import java.util.List;

public class Membership {
    private Client client;
    private Plan plan;
    private final List<AdditionalFeature> additionalFeatures;

    public Membership(Client client, Plan plan) {
        this.client = client;
        this.plan = plan;
        this.additionalFeatures = new ArrayList<>();
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void addAdditionalFeature(AdditionalFeature additionalFeature) {
        this.additionalFeatures.add(additionalFeature);
    }

    public void removeAdditionalFeature(AdditionalFeature additionalFeature) {
        this.additionalFeatures.remove(additionalFeature);
    }

    public List<AdditionalFeature> getAdditionalFeatures() {
        return this.additionalFeatures;
    }
}
