package com.green.first.springerpproject.models;

import java.time.LocalDateTime;

public class Production {
    private long productionId;
    private LocalDateTime productionDate;
    private long itemId;
    private int productionQuantity;
    private long storageId;

    public Production() {

    }

    public Production(long transferId, LocalDateTime productionDate, long itemId, int productionQuantity, long storageId) {
        this.productionId = transferId;
        this.productionDate = productionDate;
        this.itemId = itemId;
        this.productionQuantity = productionQuantity;
        this.storageId = storageId;
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

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getProductionQuantity() {
        return productionQuantity;
    }

    public void setProductionQuantity(int productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "Production{" +
                "transferId=" + productionId +
                ", productionDate=" + productionDate +
                ", itemId=" + itemId +
                ", productionQuantity=" + productionQuantity +
                ", storageId=" + storageId +
                '}';
    }
}
