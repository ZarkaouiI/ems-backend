package com.zarkaouii.backend.services;

import com.zarkaouii.backend.exceptions.EmployeeDoesNotExistException;
import com.zarkaouii.backend.models.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    EmployeeModel createEmployee(EmployeeModel employeeModel);

    List<EmployeeModel> getEmployees();

    void deleteEmployee(Long id);

    EmployeeModel getEmployeeById(Long id) throws EmployeeDoesNotExistException;

    EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) throws EmployeeDoesNotExistException;
}
