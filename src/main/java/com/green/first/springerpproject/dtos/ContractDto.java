package com.green.first.springerpproject.dtos;

import com.green.first.springerpproject.models.BusinessPartner;
import com.green.first.springerpproject.models.Employee;
import com.green.first.springerpproject.models.Item;
import com.green.first.springerpproject.models.Storage;

import java.time.LocalDate;

public class ContractDto {
    private long contractId;
    private boolean isSelling;
    private String businessPartnerName;
    private String storageName;
    private String responsibleEmployeeName;
    private String itemName;
    private int itemQuantity;
    private int totalPrice;
    private LocalDate contractDate;

    public ContractDto() {

    }

    public ContractDto(long contractId, boolean isSelling, String businessPartnerName, String storageName, String responsibleEmployeeName, String itemName, int itemQuantity, int totalPrice, LocalDate contractDate) {
        this.contractId = contractId;
        this.isSelling = isSelling;
        this.businessPartnerName = businessPartnerName;
        this.storageName = storageName;
        this.responsibleEmployeeName = responsibleEmployeeName;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.totalPrice = totalPrice;
        this.contractDate = contractDate;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getResponsibleEmployeeName() {
        return responsibleEmployeeName;
    }

    public void setResponsibleEmployeeName(String responsibleEmployeeName) {
        this.responsibleEmployeeName = responsibleEmployeeName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    @Override
    public String toString() {
        return "ContractDto{" +
                "contractId=" + contractId +
                ", isSelling=" + isSelling +
                ", businessPartnerName='" + businessPartnerName + '\'' +
                ", storageName='" + storageName + '\'' +
                ", responsibleEmployeeName='" + responsibleEmployeeName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", totalPrice=" + totalPrice +
                ", contractDate=" + contractDate +
                '}';
    }
}
