package com.zarkaouii.backend.controllers;


import com.zarkaouii.backend.entities.ProfilePhoto;
import com.zarkaouii.backend.exceptions.EmployeeDoesNotExistException;
import com.zarkaouii.backend.models.EmployeeModel;
import com.zarkaouii.backend.services.EmployeeService;
import com.zarkaouii.backend.services.PhotoProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final PhotoProfileService photoProfileService;

    @PostMapping("/employees/{id}/photoProfile")
    public boolean uploadPhotoProfile(@RequestParam("photoProfile") MultipartFile photoProfile, @PathVariable Long id) throws IOException {
        //File upload method :
        photoProfileService.uploadProfilePhoto(photoProfile, id);

        return true;
    }

    @GetMapping("/employees/{employeeId}/photoProfile")
    public ProfilePhoto getProfilePhoto(@PathVariable Long employeeId) {

        return photoProfileService.getProfilePhoto(employeeId);
    }

    @PostMapping("/employees")
    public EmployeeModel addEmployee(@RequestBody EmployeeModel employeeModel) {
        //Create employee :
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
