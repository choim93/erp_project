package com.green.first.springerpproject.models;

public class AccountAccount {
    private long accountId;
    private String accountName;
    private String accountType;

    public AccountAccount() {

    }

    public AccountAccount(long account_id, String account_name, String account_type) {
        this.accountId = account_id;
        this.accountName = account_name;
        this.accountType = account_type;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "AccountAccount{" +
                "account_id=" + accountId +
                ", account_name='" + accountName + '\'' +
                ", account_type='" + accountType + '\'' +
                '}';
    }
}
