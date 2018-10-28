package com.microhard.ga.models;

public class Me {

    private String accountID;

    private String auth;

    private String customerId;

    private String accessProfileId;

    public Me(String accountID, String auth, String customerId, String accessProfileId) {
        this.accountID = accountID;
        this.auth = auth;
        this.customerId = customerId;
        this.accessProfileId = accessProfileId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccessProfileId() {
        return accessProfileId;
    }

    public void setAccessProfileId(String accessProfileId) {
        this.accessProfileId = accessProfileId;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
