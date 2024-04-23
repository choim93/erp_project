package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.EmployeeDto;
import com.green.first.springerpproject.models.Department;
import com.green.first.springerpproject.models.Employee;
import com.green.first.springerpproject.models.Position;
import com.green.first.springerpproject.repositories.DepartmentRepository;
import com.green.first.springerpproject.repositories.EmployeeRepository;
import com.green.first.springerpproject.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    EmployeeRepository repository;
    DepartmentRepository departmentRepository;
    PositionRepository positionRepository;

    @Autowired
    public EmployeeController(EmployeeRepository repository, DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getEmployeeId());
        dto.setName(employee.getEmployeeName());
        dto.setEmail(employee.getEmployeeEmail());
        dto.setContact(employee.getEmployeeContact());
        Department department = departmentRepository.findDepartmentById(employee.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        Position position = positionRepository.findPositionById(employee.getPositionId());
        dto.setPositionName(position.getPositionName());
        dto.setSalary(employee.getEmployeeSalary());
        dto.setRecruitDate(employee.getEmployeeRecruitDate());
        dto.setRetirementDate(employee.getEmployeeRetirementDate());
        return dto;
    }

    @GetMapping("/employees")
    public String employeesPage(Model model) {
        List<Employee> employeeList = repository.findEmployeesAll();
        List<EmployeeDto> dtoList = employeeList.stream().map(this::convertToDto).toList();
        model.addAttribute("employeeList", dtoList);
        return "employees/employee_list";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployeePage(@PathVariable long id, Model model) {
        Employee employee = repository.findEmployeeById(id);
        model.addAttribute("selected_employee", employee);
        return "employees/employee_edit";
    }

    @GetMapping("/employee/add")
    public String addEmployeePage(Model model) {
        model.addAttribute("new_employee", new Employee());
        return "employees/employee_add";
    }

    @PostMapping("/employee/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        repository.insertEmployee(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employee/edit/{id}")
    public String editEmployee(@ModelAttribute Employee employee) {
        repository.updateEmployee(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        repository.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
