package com.gl.employee.controller;

import com.gl.employee.entity.Employee;
import com.gl.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage() {
        return "Welcome to Employee management";
    }

    @PostMapping(value = "/api/employees/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/employees/getAllEmployees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/employees/getEmployeeById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/api/employees/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity(employeeService.updateEmployee(employee), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/api/employees/delete/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable Long id) {
        return new ResponseEntity(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/api/employees/search/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> searchByFirstName(@PathVariable String firstName) {
        return new ResponseEntity(employeeService.searchByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping(value = "/api/employees/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> sortByFirstName(@RequestParam String firstName) {
        return new ResponseEntity(employeeService.getAllEmployeesSortedByFirstName(firstName), HttpStatus.OK);
    }
}
