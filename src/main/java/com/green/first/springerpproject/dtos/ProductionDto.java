package com.green.first.springerpproject.dtos;

import java.time.LocalDateTime;

public class ProductionDto {
    private long productionId;
    private LocalDateTime productionDate;
    private String itemName;
    private int productionQuantity;
    private String storageName;

    public ProductionDto() {
    }

    public ProductionDto(long productionId, LocalDateTime productionDate, String itemName, int productionQuantity, String storageName) {
        this.productionId = productionId;
        this.productionDate = productionDate;
        this.itemName = itemName;
        this.productionQuantity = productionQuantity;
        this.storageName = storageName;
    }

    public long getProductionId() {
        return productionId;
    }

    public void setProductionId(long productionId) {
        this.productionId = productionId;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDateTime productionDate) {
        this.productionDate = productionDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getProductionQuantity() {
        return productionQuantity;
    }

    public void setProductionQuantity(int productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    @Override
    public String toString() {
        return "ProductionDto{" +
                "productionId=" + productionId +
                ", productionDate=" + productionDate +
                ", itemName='" + itemName + '\'' +
                ", productionQuantity=" + productionQuantity +
                ", storageName='" + storageName + '\'' +
                '}';
    }
}