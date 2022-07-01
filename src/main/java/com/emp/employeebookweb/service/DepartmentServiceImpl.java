package com.emp.employeebookweb.service;

import com.emp.employeebookweb.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService{
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> showDepartmentList(int dep) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment()==dep)
                .collect(Collectors.toList());
    }

    public Employee getMinSalaryByDepartment (int dep) {
        return showDepartmentList(dep).stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get();
    }
    public Employee getMaxSalaryByDepartment (int dep) {
        return showDepartmentList(dep).stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }







}
