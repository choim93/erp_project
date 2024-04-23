package com.green.first.springerpproject.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Contract {
    private long contractId;
    private boolean isSelling;
    private String contractBusinessPartnerId;
    private long contractStorageId;
    private long contractResponsibleEmployeeId;
    private long contractItemId;

    private int contractItemQuantity;
    private LocalDate dealDate;

    public Contract() {

    }

    public Contract(long contractId, boolean isSelling, String contractBusinessPartnerId, long contractStorageId, long contractResponsibleEmployeeId, long contractItemId, int contractItemQuantity, LocalDate dealDate) {
        this.contractId = contractId;
        this.isSelling = isSelling;
        this.contractBusinessPartnerId = contractBusinessPartnerId;
        this.contractStorageId = contractStorageId;
        this.contractResponsibleEmployeeId = contractResponsibleEmployeeId;
        this.contractItemId = contractItemId;
        this.contractItemQuantity = contractItemQuantity;
        this.dealDate = dealDate;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    //    @JsonProperty("isSelling")  // 구매, 판매 처리할 때 전부 false로 변환되서 추가해봤는데 소용없다. 컨트롤러에서 직접 setSelling 메서드 호출해주자...
    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    public String getContractBusinessPartnerId() {
        return contractBusinessPartnerId;
    }

    public void setContractBusinessPartnerId(String contractBusinessPartnerId) {
        this.contractBusinessPartnerId = contractBusinessPartnerId;
    }

    public long getContractStorageId() {
        return contractStorageId;
    }

    public void setContractStorageId(long contractStorageId) {
        this.contractStorageId = contractStorageId;
    }

    public long getContractResponsibleEmployeeId() {
        return contractResponsibleEmployeeId;
    }

    public void setContractResponsibleEmployeeId(long contractResponsibleEmployeeId) {
        this.contractResponsibleEmployeeId = contractResponsibleEmployeeId;
    }

    public long getContractItemId() {
        return contractItemId;
    }

    public void setContractItemId(long contractItemId) {
        this.contractItemId = contractItemId;
    }

    public int getContractItemQuantity() {
        return contractItemQuantity;
    }

    public void setContractItemQuantity(int contractItemQuantity) {
        this.contractItemQuantity = contractItemQuantity;
    }

    public LocalDate getDealDate() {
        return dealDate;
    }

    public void setDealDate(LocalDate dealDate) {
        this.dealDate = dealDate;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", isSelling=" + isSelling +
                ", contractBusinessPartnerId='" + contractBusinessPartnerId + '\'' +
                ", contractStorageId=" + contractStorageId +
                ", contractResponsibleEmployeeId=" + contractResponsibleEmployeeId +
                ", contractItemId=" + contractItemId +
                ", contractItemQuantity=" + contractItemQuantity +
                ", dealDate=" + dealDate +
                '}';
    }
}

