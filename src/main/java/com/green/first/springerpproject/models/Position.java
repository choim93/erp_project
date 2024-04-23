package com.green.first.springerpproject.models;

public class Position {

    private long positionId;
    private String positionName;

    public Position() {

    }

    public Position(long positionId, String positionName) {
        this.positionId = positionId;
        this.positionName = positionName;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
