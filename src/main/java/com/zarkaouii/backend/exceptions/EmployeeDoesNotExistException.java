package com.zarkaouii.backend.exceptions;


public class EmployeeDoesNotExistException extends Exception {

    public EmployeeDoesNotExistException(String message) {
        super(message);
    }
}
