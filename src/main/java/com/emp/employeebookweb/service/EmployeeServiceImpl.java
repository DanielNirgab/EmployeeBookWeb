package com.emp.employeebookweb.service;

import com.emp.employeebookweb.exception.EmplFullNameIsIncorrect;
import com.emp.employeebookweb.model.Employee;
import com.emp.employeebookweb.exception.EmployeeAlreadyAddedException;
import com.emp.employeebookweb.exception.EmployeeNotFoundException;
import com.emp.employeebookweb.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(Employee employee) {
        return employee.getSecondName();
    }

    @Override
    public Employee addEmployee(String firstName, String secondName, int depart, double salary) {
        Employee employee = new Employee(firstName, secondName, depart, salary);
        checkEmplFullName(employee);
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(getKey(employee), employee);
        } else {
            throw new EmployeeStorageIsFullException();
        }
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String secondName, int depart, double salary) {
        Employee employee = new Employee(firstName, secondName, depart, salary);
        String key = getKey(employee);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String secondName, int depart, double salary) {
        Employee employee = new Employee(firstName, secondName, depart, salary);
        String key = getKey(employee);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    private boolean checkEmplFullName (Employee employee) {
        if (StringUtils.isAlpha(employee.getFirstName()) && StringUtils.isAlpha(employee.getSecondName())) {
            employee.setFirstName(employee.getFirstName().toLowerCase(Locale.ROOT));
            employee.setSecondName(employee.getSecondName().toLowerCase(Locale.ROOT));
            employee.setFirstName(StringUtils.capitalize(employee.getFirstName()));
            employee.setSecondName(StringUtils.capitalize(employee.getSecondName()));
            return true;
        } else {
            throw new EmplFullNameIsIncorrect();
        }
    }

}
