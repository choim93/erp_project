package com.green.first.springerpproject.dtos;

import java.time.LocalDate;

public class EmployeeDto {
    private long id;
    private String Name;
    private String Email;
    private String contact;
    private String departmentName;
    private String positionName;
    private int salary;
    private LocalDate recruitDate, retirementDate;

    public EmployeeDto() {

    }

    public EmployeeDto(long id, String name, String email, String contact, String department, String position, int salary, LocalDate recruitDate, LocalDate retirementDate) {
        this.id = id;
        Name = name;
        Email = email;
        this.contact = contact;
        this.departmentName = department;
        this.positionName = position;
        this.salary = salary;
        this.recruitDate = recruitDate;
        this.retirementDate = retirementDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getRecruitDate() {
        return recruitDate;
    }

    public void setRecruitDate(LocalDate recruitDate) {
        this.recruitDate = recruitDate;
    }

    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", contact='" + contact + '\'' +
                ", department='" + departmentName + '\'' +
                ", position='" + positionName + '\'' +
                ", salary=" + salary +
                ", recruitDate=" + recruitDate +
                ", retirementDate=" + retirementDate +
                '}';
    }
}
