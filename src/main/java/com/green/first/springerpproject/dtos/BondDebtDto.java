package com.green.first.springerpproject.dtos;

public class BondDebtDto {
    private String businessPartnerName;
    private boolean isSelling;
    private int totalAmount;
    private int totalAmountRank;
    private String riskLevel;

    public BondDebtDto() {
    }

    public BondDebtDto(String businessPartnerName, boolean isSelling, int totalAmount, int totalAmountRank, String riskLevel) {
        this.businessPartnerName = businessPartnerName;
        this.isSelling = isSelling;
        this.totalAmount = totalAmount;
        this.totalAmountRank = totalAmountRank;
        this.riskLevel = riskLevel;
    }

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }

    public boolean isSelling() {
        return isSelling;
    }

    public void setSelling(boolean selling) {
        isSelling = selling;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmountRank() {
        return totalAmountRank;
    }

    public void setTotalAmountRank(int totalAmountRank) {
        this.totalAmountRank = totalAmountRank;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {
        return "BondDebtDto{" +
                "businessPartnerName='" + businessPartnerName + '\'' +
                ", isSelling=" + isSelling +
                ", totalAmount=" + totalAmount +
                ", totalAmountRank=" + totalAmountRank +
                ", riskLevel='" + riskLevel + '\'' +
                '}';
    }
}

