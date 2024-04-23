package com.green.first.springerpproject.dtos;

import java.time.LocalDateTime;

public class TransferDto {
    private long transferId;
    private String bankAccountNumber;
    private String bankAccountName;
    private int volume;
    private String info;
    private LocalDateTime transferDatetime;
    private int balance;
    private String businessPartnerName;
    private String accountName;

    public TransferDto() {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "TransferDto{" +
                "transferId=" + transferId +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", volume=" + volume +
                ", info='" + info + '\'' +
                ", transferDatetime=" + transferDatetime +
                ", balance=" + balance +
                ", businessPartnerName='" + businessPartnerName + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}