package com.emp.employeebookweb.service;

import com.emp.employeebookweb.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Boolean> getByDepartment(Integer num);

    List<Employee> checkDepartmentMinSalary(Integer num);

    List<Employee> checkDepartmentMaxSalary(Integer num);

    List<Employee> getAllDepartmentEmployees();

}
