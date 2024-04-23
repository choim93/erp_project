package com.green.first.springerpproject.dtos;

import com.green.first.springerpproject.models.Item;
import com.green.first.springerpproject.models.ProductionRawMaterial;
import com.green.first.springerpproject.models.Storage;

import java.time.LocalDate;
import java.util.List;

public class ProductionDto2 {
    private LocalDate productionDate;
    private long productId;
    private int productQuantity;
    private long destinationStorageId;
    List<ProductionRawMaterial> rawMaterials;

    public ProductionDto2() {

    }

    public ProductionDto2(LocalDate productionDate, long productId, int productQuantity, long destinationStorageId, List<ProductionRawMaterial> rawMaterials) {
        this.productionDate = productionDate;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.destinationStorageId = destinationStorageId;
        this.rawMaterials = rawMaterials;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public long getDestinationStorageId() {
        return destinationStorageId;
    }

    public void setDestinationStorageId(long destinationStorageId) {
        this.destinationStorageId = destinationStorageId;
    }

    public List<ProductionRawMaterial> getRawMaterials() {
        return rawMaterials;
    }

    public void setRawMaterials(List<ProductionRawMaterial> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

    @Override
    public String toString() {
        return "ProductionDto2{" +
                "productionDate=" + productionDate +
                ", productId=" + productId +
                ", productQuantity=" + productQuantity +
                ", destinationStorageId=" + destinationStorageId +
                ", rawMaterials=" + rawMaterials +
                '}';
    }
}
