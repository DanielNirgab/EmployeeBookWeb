package com.emp.employeebookweb.service;

import com.emp.employeebookweb.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee addEmployee(String firstName, String secondName, int depart, double salary);

    Employee removeEmployee(String firstName, String secondName, int depart, double salary);

    Employee findEmployee(String firstName, String secondName, int depart, double salary);

    List<Employee> getEmployees();
}
