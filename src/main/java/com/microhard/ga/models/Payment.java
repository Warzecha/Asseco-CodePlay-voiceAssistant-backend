package com.microhard.ga.models;


import com.microhard.ga.BasicDB;

import javax.persistence.Entity;
import java.util.Date;

import static com.microhard.ga.BasicDB.*;

public class Payment {

    private int amount;

    private String currency;

    private String[] description;

    private String intent;

    private Date realizationDate;

    private String recipientAccountNo;

    private String[] recipientAddress;

    private String[] recipientName;

    private String remitterAccountId;

    public Payment(AssistantPayment assistantPayment) {
        this.amount = new Integer(assistantPayment.getAmount());
        this.currency = assistantPayment.getCurrency();
        this.intent = "realize";
        this.realizationDate = new Date(System.currentTimeMillis());
        String[] desc = {assistantPayment.getDescription()};
        this.description = desc;
        this.remitterAccountId = me.getAccountID();

        for (Receiver receiver : myPeers) {
            if (receiver.getName().equals(assistantPayment.getName())) {
                String[] name = {receiver.getName() + " " + receiver.getSurname()};
                this.recipientName = name;
                this.recipientAccountNo = receiver.getAccountNumber();
                this.recipientAddress = receiver.getAddress();
            }
        }

    }

    public Payment() {

    }

    public Payment(int amount, String currency, String[] description, String recipientAccountNo, String[] recipientAddress, String[] recipientName, String remitterAccountId) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.intent = "realize";
        this.realizationDate = new Date(System.currentTimeMillis());
        this.recipientAccountNo = recipientAccountNo;
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
        this.remitterAccountId = remitterAccountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public String getRecipientAccountNo() {
        return recipientAccountNo;
    }

    public void setRecipientAccountNo(String recipientAccountNo) {
        this.recipientAccountNo = recipientAccountNo;
    }

    public String[] getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String[] recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String[] getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String[] recipientName) {
        this.recipientName = recipientName;
    }

    public String getRemitterAccountId() {
        return remitterAccountId;
    }

    public void setRemitterAccountId(String remitterAccountId) {
        this.remitterAccountId = remitterAccountId;
    }
}
