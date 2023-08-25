package com.zarkaouii.backend.mappers;

import com.zarkaouii.backend.entities.Employee;
import com.zarkaouii.backend.models.EmployeeModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public Employee employeeModelToEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeModel, employee);
        return employee;
    }

    public EmployeeModel employeeToEmployeeModel(Employee employee) {
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employee, employeeModel);
        return employeeModel;
    }
}
