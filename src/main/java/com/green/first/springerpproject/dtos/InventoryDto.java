package com.green.first.springerpproject.dtos;

public class InventoryDto {
    private long inventoryId;
    private int itemQuantity;
    private String itemName;
    private String storageName;
    private String description;
    private String sum_quantity;
    private long storage_id;

    public InventoryDto() {
    }

    public InventoryDto(long inventoryId, int itemQuantity, String itemName, String storageName, String description, String sum_quantity, long storage_id) {
        this.inventoryId = inventoryId;
        this.itemQuantity = itemQuantity;
        this.itemName = itemName;
        this.storageName = storageName;
        this.description = description;
        this.sum_quantity = sum_quantity;
        this.storage_id = storage_id;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSum_quantity() {
        return sum_quantity;
    }

    public void setSum_quantity(String sum_quantity) {
        this.sum_quantity = sum_quantity;
    }

    public long getStorage_id() {
        return storage_id;
    }

    public void setStorage_id(long storage_id) {
        this.storage_id = storage_id;
    }

    @Override
    public String toString() {
        return "InventoryDto{" +
                "inventoryId=" + inventoryId +
                ", itemQuantity=" + itemQuantity +
                ", itemName='" + itemName + '\'' +
                ", storageName='" + storageName + '\'' +
                ", description='" + description + '\'' +
                ", sum_quantity='" + sum_quantity + '\'' +
                ", storage_id=" + storage_id +
                '}';
    }
}