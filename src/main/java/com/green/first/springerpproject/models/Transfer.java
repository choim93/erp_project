package com.green.first.springerpproject.models;

import java.time.LocalDateTime;

public class Transfer {
    private long transferId;
    private String bankAccountNumber;
    private String bankAccountName;
    private int volume;
    private long accountId;
    private String info;
    private String businessPartnerId;

    private LocalDateTime transferDatetime;

    private int balance;

    public Transfer() {

    }

    public Transfer(long transferId, String bankAccountNumber, String bankAccountName, int volume, long accountId, String info, String businessPartnerId, LocalDateTime transferDateTime, int balance) {
        this.transferId = transferId;
        this.bankAccountNumber = bankAccountNumber;
        this.bankAccountName = bankAccountName;
        this.volume = volume;
        this.accountId = accountId;
        this.info = info;
        this.businessPartnerId = businessPartnerId;
        this.transferDatetime = transferDateTime;
        this.balance = balance;
    }

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(String businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    public LocalDateTime getTransferDatetime() {
        return transferDatetime;
    }

    public void setTransferDatetime(LocalDateTime transferDatetime) {
        this.transferDatetime = transferDatetime;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", volume=" + volume +
                ", accountId=" + accountId +
                ", info='" + info + '\'' +
                ", businessPartnerId='" + businessPartnerId + '\'' +
                ", transferDateTime=" + transferDatetime +
                ", balance=" + balance +
                '}';
    }
}