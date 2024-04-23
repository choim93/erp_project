package com.green.first.springerpproject.models;

public class Inventory {
    private long inventoryId;
    private long itemId;
    private long storageId;
    private int itemQuantity;
    private Long moveReasonId;

    public Inventory() {

    }

    public Inventory(long inventoryId, long itemId, long storageId, int itemQuantity, Long moveReasonId) {
        this.inventoryId = inventoryId;
        this.itemId = itemId;
        this.storageId = storageId;
        this.itemQuantity = itemQuantity;
        this.moveReasonId = moveReasonId;
    }

    public long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Long getMoveReasonId() {
        return moveReasonId;
    }

    public void setMoveReasonId(Long moveReasonId) {
        this.moveReasonId = moveReasonId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", itemId=" + itemId +
                ", storageId=" + storageId +
                ", itemQuantity=" + itemQuantity +
                ", moveReasonId=" + moveReasonId +
                '}';
    }
}
