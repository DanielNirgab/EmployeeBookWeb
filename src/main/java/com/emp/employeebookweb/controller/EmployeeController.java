package com.emp.employeebookweb.controller;


import com.emp.employeebookweb.model.Employee;
import com.emp.employeebookweb.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;


    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam ("firstName") String firstName,
                        @RequestParam ("secondName") String secondName,
                        @RequestParam ("department") int depart,
                        @RequestParam ("salary") double salary) {
        return employeeService.addEmployee(firstName, secondName, depart, salary);

    }

    @GetMapping("/remove")
    public Employee remove (@RequestParam ("firstName") String firstName,
                            @RequestParam ("secondName") String secondName,
                            @RequestParam ("department") int depart,
                            @RequestParam ("salary") double salary) {
        return employeeService.removeEmployee(firstName, secondName, depart, salary);
    }

    @GetMapping("/find")
    public Employee find (@RequestParam ("firstName") String firstName,
                          @RequestParam ("secondName") String secondName,
                          @RequestParam ("department") int depart,
                          @RequestParam ("salary") double salary) {
        return employeeService.findEmployee(firstName, secondName, depart, salary);
    }

    @GetMapping("/")
    public List<Employee> getList () {
        return employeeService.getEmployees();
    }


}
