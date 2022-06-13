package com.emp.employeebookweb.service;

import com.emp.employeebookweb.Employee;
import com.emp.employeebookweb.exception.EmployeeAlreadyAddedException;
import com.emp.employeebookweb.exception.EmployeeNotFoundException;
import com.emp.employeebookweb.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
public class EmployeeServiceImpl {
    private static final int LIMIT = 3;
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String secondName) {
        Employee employee = new Employee(firstName, secondName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
        } else {
            throw new EmployeeStorageIsFullException();
        }
        return employee;
    }

    public Employee removeEmployee(String firstName, String secondName) {
        Employee employee = new Employee(firstName, secondName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
                return employee;
    }

    public Employee findEmployee(String firstName, String secondName) {
        Employee employee = new Employee(firstName, secondName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getEmployees () {
        return new ArrayList<>(employees);
    }

}
