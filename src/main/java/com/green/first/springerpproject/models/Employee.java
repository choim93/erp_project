package com.green.first.springerpproject.models;

import java.time.LocalDate;


public class Employee {
    private long employeeId;
    private String password;
    private long departmentId;
    private String employeeName;
    private String employeeEmail;
    private String employeeContact;
    private long positionId;
    private int employeeSalary;
    private LocalDate employeeRecruitDate;
    private LocalDate employeeRetirementDate;

    public Employee() {

    }

    public Employee(long employeeId, String password, long departmentId, String employeeName, String employeeEmail,
                    String employeeContact, long positionId, int employeeSalary, LocalDate employeeRecruitDate, LocalDate employeeRetirementDate) {
        this.employeeId = employeeId;
        this.password = password;
        this.departmentId = departmentId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeContact = employeeContact;
        this.positionId = positionId;
        this.employeeSalary = employeeSalary;
        this.employeeRecruitDate = employeeRecruitDate;
        this.employeeRetirementDate = employeeRetirementDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public int getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(int employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public LocalDate getEmployeeRecruitDate() {
        return employeeRecruitDate;
    }

    public void setEmployeeRecruitDate(LocalDate employeeRecruitDate) {
        this.employeeRecruitDate = employeeRecruitDate;
    }

    public LocalDate getEmployeeRetirementDate() {
        return employeeRetirementDate;
    }

    public void setEmployeeRetirementDate(LocalDate employeeRetirementDate) {
        this.employeeRetirementDate = employeeRetirementDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", password='" + password + '\'' +
                ", departmentId=" + departmentId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeContact='" + employeeContact + '\'' +
                ", positionId=" + positionId +
                ", employeeSalary=" + employeeSalary +
                ", employeeRecruitDate=" + employeeRecruitDate +
                ", employeeRetirementDate=" + employeeRetirementDate +
                '}';
    }
}
