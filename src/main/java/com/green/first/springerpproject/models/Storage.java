package com.green.first.springerpproject.models;

public class Storage {
    private long storageId;
    private String storageName;
    private String storageAddress;

    public Storage() {
    }

    public Storage(long storageId, String storageName, String storageAddress) {
        this.storageId = storageId;
        this.storageName = storageName;
        this.storageAddress = storageAddress;
    }

    public long getStorageId() {
        return storageId;
    }

    public void setStorageId(long storageId) {
        this.storageId = storageId;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    @Override
    public String toString() {
        return "Storages{" +
                "storageId=" + storageId +
                ", storageName='" + storageName + '\'' +
                ", storageAddress='" + storageAddress + '\'' +
                '}';
    }
}
