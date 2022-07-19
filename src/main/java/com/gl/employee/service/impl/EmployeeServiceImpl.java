package com.gl.employee.service.impl;

import com.gl.employee.entity.Employee;
import com.gl.employee.repository.EmployeeRepository;
import com.gl.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            Employee savedEmployee = existingEmployee.get();
            savedEmployee.setId(employee.getId());
            savedEmployee.setFirstName(employee.getFirstName());
            savedEmployee.setLastName(employee.getLastName());
            savedEmployee.setEmail(employee.getEmail());
            return employeeRepository.save(savedEmployee);
        }
        return null;
    }

    @Override
    public String deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
        return "Deleted Employee id is: " + id;
    }

    @Override
    public List<Employee> searchByFirstName(String firstName) {
        return employeeRepository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public List<Employee> getAllEmployeesSortedByFirstName(String order) {
        if (order.equalsIgnoreCase("desc")) {
            return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
        } else {
            return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        }
    }


}
