package com.emp.employeebookweb.controller;


import com.emp.employeebookweb.Employee;
import com.emp.employeebookweb.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;


    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("add")
    public Employee add(@RequestParam ("firstName") String firstName,
                        @RequestParam ("secondName") String secondName) {
        return employeeService.addEmployee(firstName, secondName);

    }

    @GetMapping("remove")
    public Employee remove (@RequestParam ("firstName") String firstName,
                        @RequestParam ("secondName") String secondName) {
        return employeeService.removeEmployee(firstName, secondName);
    }

    @GetMapping("find")
    public Employee find (@RequestParam ("firstName") String firstName,
                            @RequestParam ("secondName") String secondName) {
        return employeeService.findEmployee(firstName, secondName);
    }

    @GetMapping("/")
    public List<Employee> getList () {
        return employeeService.getEmployees();
    }



}
