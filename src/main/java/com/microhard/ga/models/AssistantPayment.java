package com.microhard.ga.models;

public class AssistantPayment {

     private String amount;

     private String currency;

     private String name;

     private String description;

    public AssistantPayment(String amount, String currency, String name, String description) {
        this.amount = amount;
        this.currency = currency;
        this.name = name;
        this.description = description;
    }

    public AssistantPayment() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
