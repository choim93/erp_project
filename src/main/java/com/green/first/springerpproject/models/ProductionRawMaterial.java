package com.green.first.springerpproject.models;

public class ProductionRawMaterial {
    private long id;
    private long productionId;
    private long rawMaterialId;
    private int rawMaterialQuantity;
    private long storageId;

    public ProductionRawMaterial() {

    }

    public ProductionRawMaterial(long id, long productionId, long rawMaterialId, int rawMaterialQuantity, long stroageId) {
        this.id = id;
        this.productionId = productionId;
        this.rawMaterialId = rawMaterialId;
        this.rawMaterialQuantity = rawMaterialQuantity;
        this.storageId = stroageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductionId() {
        return productionId;
    }

    public void setProductionId(long productionId) {
        this.productionId = productionId;
    }

    public long getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(long rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public int getRawMaterialQuantity() {
        return rawMaterialQuantity;
    }

    public void setRawMaterialQuantity(int rawMaterialQuantity) {
        this.rawMaterialQuantity = rawMaterialQuantity;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    @Override
    public String toString() {
        return "ProductionRawMaterial{" +
                "id=" + id +
                ", productionId=" + productionId +
                ", rawMaterialId=" + rawMaterialId +
                ", rawMaterialQuantity=" + rawMaterialQuantity +
                ", storageId=" + storageId +
                '}';
    }
}
