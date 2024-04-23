package com.green.first.springerpproject.models;

public class Department {
    private long departmentId;
    private String departmentName;
    private long departmentHeadId;

    public Department() {

    }

    public Department(long departmentId, String departmentName, long departmentHeadId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentHeadId = departmentHeadId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(long departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }

    @Override
    public String toString() {
        return "department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", departmentHeadId=" + departmentHeadId +
                '}';
    }
}
