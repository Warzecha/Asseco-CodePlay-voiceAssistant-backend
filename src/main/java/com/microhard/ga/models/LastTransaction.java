package com.microhard.ga.models;

public class LastTransaction {

    private String name;

    private String toWhom;

    private String cost;

    private String currency;

    public LastTransaction(String name, String toWhom, String cost, String currency) {
        this.name = name;
        this.toWhom = toWhom;
        this.cost = cost;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
