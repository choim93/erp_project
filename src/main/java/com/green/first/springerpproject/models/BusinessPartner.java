package com.green.first.springerpproject.models;

public class BusinessPartner {
    private String businessPartnerId;
    private String businessPartnerName;
    private String businessPartnerAddress;
    private String businessPartnerContact;

    public BusinessPartner() {

    }

    public BusinessPartner(String businessPartnerId, String businessPartnerName,
                           String businessPartnerAddress, String businessPartnerContact) {
        this.businessPartnerId = businessPartnerId;
        this.businessPartnerName = businessPartnerName;
        this.businessPartnerAddress = businessPartnerAddress;
        this.businessPartnerContact = businessPartnerContact;
    }

    public String getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(String businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }

    public String getBusinessPartnerAddress() {
        return businessPartnerAddress;
    }

    public void setBusinessPartnerAddress(String businessPartnerAddress) {
        this.businessPartnerAddress = businessPartnerAddress;
    }

    public String getBusinessPartnerContact() {
        return businessPartnerContact;
    }

    public void setBusinessPartnerContact(String businessPartnerContact) {
        this.businessPartnerContact = businessPartnerContact;
    }

    @Override
    public String toString() {
        return "BusinessPartners{" +
                "businessPartnerId='" + businessPartnerId + '\'' +
                ", businessPartnerName='" + businessPartnerName + '\'' +
                ", businessPartnerAddress='" + businessPartnerAddress + '\'' +
                ", businessPartnerContact='" + businessPartnerContact + '\'' +
                '}';
    }

    public static class ProductionRawMaterial {
        private long id;
        private long productionId;
        private long rawMaterialId;
        private int rawMaterialQuantity;
        private long storageId;

        public ProductionRawMaterial() {

        }

        public ProductionRawMaterial(long id, long productionId, long rawMaterialId, int rawMaterialQuantity, long storageId) {
            this.id = id;
            this.productionId = productionId;
            this.rawMaterialId = rawMaterialId;
            this.rawMaterialQuantity = rawMaterialQuantity;
            this.storageId = storageId;
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
}
