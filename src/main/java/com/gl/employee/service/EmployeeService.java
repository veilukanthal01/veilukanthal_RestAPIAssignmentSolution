package com.gl.employee.service;

import com.gl.employee.entity.Employee;

import java.util.List;


public interface EmployeeService {
    public Employee saveEmployee(Employee employee);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(Long id);
    public Employee updateEmployee(Employee employee);
    public String deleteEmployeeById(Long id);
}
