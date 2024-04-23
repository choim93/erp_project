package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemp;

    static final String SELECT_EMPLOYEES_ALL = "select * from employees";
    static final String SELECT_EMPLOYEES_BYID = "select * from employees as e where e.employee_id = ?";
    static final String SELECT_EMPLOYEES_BY_EMAIL = "SELECT * FROM employees WHERE email = ?";
    static final String INSERT_EMPLOYEE = "INSERT INTO employees ( password, department_id, employee_name, employee_email, employee_contact, position_id, employee_salary, employee_recruit_date ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
    static final String UPDATE_EMPLOYEE_BYID = "update employees set password = ?," +
            "department_id = ? , employee_name = ? ,employee_email = ? ,employee_contact = ? ," +
            "position_id = ?,employee_salary = ?,employee_contact = ?,employee_recruit_date = ?," +
            "employee_retirement_date = ? where employee_id = ? ";
    static final String DELETE_EMPLOYEE_BYID = "delete from employees as e where e.employee_id = ?";


    public List<Employee> findEmployeesAll() {
        return jdbcTemp.query(SELECT_EMPLOYEES_ALL, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee findEmployeeById(long id) {
        return jdbcTemp.queryForObject(SELECT_EMPLOYEES_BYID, new BeanPropertyRowMapper<>(Employee.class), id);
    }

    public Employee findEmployeeByEmail(String email) {
        return jdbcTemp.queryForObject(SELECT_EMPLOYEES_BYID, new BeanPropertyRowMapper<>(Employee.class), email);
    }

    public void insertEmployee(Employee employee) {
        jdbcTemp.update(INSERT_EMPLOYEE, employee.getPassword(), employee.getDepartmentId(),
                employee.getEmployeeName(), employee.getEmployeeEmail(), employee.getEmployeeContact(), employee.getPositionId(),
                employee.getEmployeeSalary(), employee.getEmployeeRecruitDate());
    }

    public void updateEmployee(Employee employee) {
        jdbcTemp.update(UPDATE_EMPLOYEE_BYID,
                employee.getPassword(),
                employee.getDepartmentId(),
                employee.getEmployeeName(),
                employee.getEmployeeEmail(),
                employee.getEmployeeContact(),
                employee.getPositionId(),
                employee.getEmployeeSalary(),
                employee.getEmployeeContact(),
                employee.getEmployeeRecruitDate(),
                employee.getEmployeeRetirementDate(),
                employee.getEmployeeId());
    }

    public void deleteEmployeeById(long id) {
        jdbcTemp.update(DELETE_EMPLOYEE_BYID, id);
    }
}
