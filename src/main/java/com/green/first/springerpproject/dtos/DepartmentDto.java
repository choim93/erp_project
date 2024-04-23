package com.green.first.springerpproject.dtos;

public class DepartmentDto {
    private long departmentId;
    private String departmentName;
    private String employeeName;

    public DepartmentDto() {
    }

    public DepartmentDto(long departmentId, String departmentName, String employeeName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeName = employeeName;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}

