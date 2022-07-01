package com.emp.employeebookweb.controller;

import com.emp.employeebookweb.model.Employee;
import com.emp.employeebookweb.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public List<Employee> getFromDepart(@RequestParam("departmentId") Integer num) {
        return departmentService.showDepartmentList(num);
    }
    @GetMapping("/min-salary")
    public Employee getMinSalary(@RequestParam("departmentId") Integer num) {
        return departmentService.getMinSalaryByDepartment(num);
    }
    @GetMapping("/max-salary")
    public Employee getMaxSalary(@RequestParam("departmentId") Integer num) {
        return departmentService.getMaxSalaryByDepartment(num);
    }
}

