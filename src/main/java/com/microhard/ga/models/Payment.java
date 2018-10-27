package com.microhard.ga.models;


import java.util.Date;

public class Payment {

    private Double amount;

    private String currency;

    private String[] description;

    private String intent;

    private Date realizationDate;

    private String recipientAccountNo;

    private String[] recipientAddress;

    private String[] recipientName;

    private String remitterAccountId;

    public Payment() {

    }

    public Payment(Double amount, String currency, String[] description, String intent, Date realizationDate, String recipientAccountNo, String[] recipientAddress, String[] recipientName, String remitterAccountId) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.intent = intent;
        this.realizationDate = new Date(System.currentTimeMillis());
        this.recipientAccountNo = recipientAccountNo;
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
        this.remitterAccountId = remitterAccountId;
    }
}
