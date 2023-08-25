package com.zarkaouii.backend.controllers;


import com.zarkaouii.backend.exceptions.EmployeeDoesNotExistException;
import com.zarkaouii.backend.models.EmployeeModel;
import com.zarkaouii.backend.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public EmployeeModel addEmployee(@RequestBody EmployeeModel employeeModel) {
        return employeeService.createEmployee(employeeModel);
    }

    @GetMapping("/employees")
    public List<EmployeeModel> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public EmployeeModel getEmployee(@PathVariable Long id) throws EmployeeDoesNotExistException {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public EmployeeModel updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel employeeModel) throws EmployeeDoesNotExistException {
        return employeeService.updateEmployee(id, employeeModel);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
