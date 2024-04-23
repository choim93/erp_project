package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.dtos.DepartmentDto;
import com.green.first.springerpproject.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    JdbcTemplate template;

    @Autowired
    DepartmentRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final static String SELECT_DEPARTMENT_BY_ID_QUERY = "SELECT * FROM departments WHERE department_id = ?";
    private final static String SELECT_DEPARTMENT_QUERY = "SELECT * FROM departments";
    private final static String INSERT_DEPARTMENT_QUERY = "INSERT INTO departments (department_name, department_head_id) VALUES (?, ?)";
    private final static String UPDATE_DEPARTMENT_BY_ID_QUERY = "UPDATE departments SET department_name = ?, department_head_id = ? WHERE department_id = ?";
    private final static String DELETE_DEPARTMENT_BY_ID_QUERY = "DELETE FROM departments WHERE department_id = ?";
    //부서장 id 말고 부서장 이름으로 select
    private final static String SELECT_DEPARTMENT_WHIT_DEPARTMENT_HEAD_NAME = """
            SELECT d.department_id,d.department_name,e.employee_name
            FROM departments as d
            LEFT OUTER join employees as e
            on d.department_head_id = e.employee_id                    
            """;

    public Department findDepartmentById(long id) {
        return template.queryForObject(SELECT_DEPARTMENT_BY_ID_QUERY, new DepartmentRowMapper(), id);
    }

    public List<Department> findAllDepartment() {
        return template.query(SELECT_DEPARTMENT_QUERY, new DepartmentRowMapper());
    }

    public void insertDepartment(String departmentName, Long departmentHeadId) {
        template.update(INSERT_DEPARTMENT_QUERY, departmentName, departmentHeadId);
    }

    public void updateDepartmentById(Department department) {
        template.update(UPDATE_DEPARTMENT_BY_ID_QUERY, department.getDepartmentName(), department.getDepartmentHeadId(), department.getDepartmentId());
    }

    public void deleteDepartmentById(long id) {
        template.update(DELETE_DEPARTMENT_BY_ID_QUERY, id);
    }

    public List<DepartmentDto> findDepartmentWithDepartmentHeadName() {
        return template.query(SELECT_DEPARTMENT_WHIT_DEPARTMENT_HEAD_NAME, new BeanPropertyRowMapper<>(DepartmentDto.class));
    }
}
