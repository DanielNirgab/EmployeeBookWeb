package com.emp.employeebookweb;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String secondName;

    public Employee(String name, String secondName) {
        this.firstName = name;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(secondName, employee.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }

    @Override
    public String toString() {
        return "Сотрудник - " +
                "Имя:'" + firstName + '\'' +
                ", Фамилия:'" + secondName + '\'';
    }
}
