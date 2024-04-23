package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(rs.getLong("department_id"));
        department.setDepartmentName(rs.getString("department_name"));
        department.setDepartmentHeadId(rs.getLong("department_head_id"));

        return department;
    }
}
