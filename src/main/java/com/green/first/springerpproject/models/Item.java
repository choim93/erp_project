package com.green.first.springerpproject.models;

import java.time.LocalDate;

public class Item {
    private long itemId;
    private boolean rawMaterial;
    private int itemPrice;
    private String itemName;

    private LocalDate effective_date;

    public Item() {
    }

    public Item(long itemId, boolean rawMaterial, int itemPrice, String itemName, LocalDate effective_date) {
        this.itemId = itemId;
        this.rawMaterial = rawMaterial;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
        this.effective_date = effective_date;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public boolean isRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(boolean rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDate getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(LocalDate effective_date) {
        this.effective_date = effective_date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", rawMaterial=" + rawMaterial +
                ", itemPrice=" + itemPrice +
                ", itemName='" + itemName + '\'' +
                ", effective_date=" + effective_date +
                '}';
    }
}
