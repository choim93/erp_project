package com.green.first.springerpproject.models;

public class InventoryMoveReason {
    private long reasonId;
    private String description;

    public InventoryMoveReason() {
    }

    public InventoryMoveReason(long reasonId, String description) {
        this.reasonId = reasonId;
        this.description = description;
    }

    public long getReasonId() {
        return reasonId;
    }

    public void setReasonId(long reasonId) {
        this.reasonId = reasonId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "inventoryMoveReasons{" +
                "reasonId=" + reasonId +
                ", description='" + description + '\'' +
                '}';
    }
}