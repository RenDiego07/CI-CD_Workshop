package com.example.model;

public class Client {
    private int id;
    private String name;
    private int age;
    private Membership membership;


    public Client(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return String.valueOf(age);
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }    

}
