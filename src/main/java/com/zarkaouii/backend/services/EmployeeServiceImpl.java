package com.zarkaouii.backend.services;

import com.zarkaouii.backend.entities.Employee;
import com.zarkaouii.backend.exceptions.EmployeeDoesNotExistException;
import com.zarkaouii.backend.mappers.EmployeeMapper;
import com.zarkaouii.backend.models.EmployeeModel;
import com.zarkaouii.backend.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeModel createEmployee(EmployeeModel employeeModel) {
        Employee employee = employeeMapper.employeeModelToEmployee(employeeModel);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeModel(savedEmployee);
    }

    @Override
    public List<EmployeeModel> getEmployees() {
        //TODO Use streams api
        //TODO I prefer the old fashion actually!

        List<Employee> employeesList = employeeRepository.findAll();
        List<EmployeeModel> employees = employeesList
                .stream()
                .map(emp -> {
                            EmployeeModel empModel = new EmployeeModel();
                            empModel.setId(emp.getId());
                            empModel.setFirstName(emp.getFirstName());
                            empModel.setLastName(emp.getLastName());
                            empModel.setEmail(emp.getEmail());
                            return empModel;
                        }
                ).collect(Collectors.toList());

//        for(Employee emp : employeesList) {
//            employees.add(employeeMapper.employeeToEmployeeModel(emp));
//        }
        return employees;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeModel getEmployeeById(Long id) throws EmployeeDoesNotExistException {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeDoesNotExistException("Employee doesn't exist!")
        );
        return employeeMapper.employeeToEmployeeModel(employee);
    }

    @Override
    public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) throws EmployeeDoesNotExistException {
        EmployeeModel emp = getEmployeeById(id);
        if(emp != null) {
//            Employee employee = new Employee(emp.getId(),
//          employeeModel.getFirstName(),
//            employeeModel.getLastName(),
//            employeeModel.getEmail());
            Employee employee = Employee.builder()
                    .firstName(employeeModel.getFirstName())
                    .lastName(employeeModel.getLastName())
                    .email(employeeModel.getEmail())
                    .build();
            employeeRepository.save(employee);
        }
        return emp;
    }

}
