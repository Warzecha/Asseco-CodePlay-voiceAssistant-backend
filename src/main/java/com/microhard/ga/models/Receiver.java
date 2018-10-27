package com.microhard.ga.models;

public class Receiver {

    private String name;

    private String surname;

    private String accountNumber;

    private String[] address;

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public Receiver(String name, String surname, String accountNumber, String[] address) {
        this.name = name;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
