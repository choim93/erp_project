package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.dtos.DepartmentDto;
import com.green.first.springerpproject.models.Department;
import com.green.first.springerpproject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepartmentController {

    DepartmentRepository repository;

    @Autowired
    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/departments")
    public String departmentsPage(Model model) {
        List<DepartmentDto> departmentDtos = repository.findDepartmentWithDepartmentHeadName();
        model.addAttribute("departments_list", departmentDtos);
        return "departments/departments_list";
    }

    @GetMapping("/departments/edit/{id}")
    public String editDepartmentPage(@PathVariable long id, Model model) {
        model.addAttribute("selected_department", repository.findDepartmentById(id));
        return "departments/departments_edit";
    }

    @PostMapping("/departments/edit/{id}")
    public String editDepartment(@ModelAttribute Department department) {
        repository.updateDepartmentById(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/add")
    public String addDepartmentPage(Model model) {
        model.addAttribute("new_department", new Department());
        return "departments/departments_add";
    }

    @PostMapping("/departments/add")
    public String addDepartment(@ModelAttribute Department department) {
        repository.insertDepartment(department.getDepartmentName(), department.getDepartmentHeadId());
        return "redirect:/departments";
    }

    @PostMapping("/departments/delete/{id}")
    public String addDepartment(@PathVariable long id) {
        repository.deleteDepartmentById(id);
        return "redirect:/departments";
    }
}
