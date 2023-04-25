package com.github.aza0602.hw_2_10_libraries.controller;


import com.github.aza0602.hw_2_10_libraries.exception.IncorrectLastNameException;
import com.github.aza0602.hw_2_10_libraries.exception.IncorrectNameException;
import com.github.aza0602.hw_2_10_libraries.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("name") String name,
                        @RequestParam("surname") String surname,
                        @RequestParam int department,
                        @RequestParam int salary) {
        return employeeService.add(name, surname, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("name") String name,
                           @RequestParam("surname") String surname) {
        return employeeService.remove(name, surname);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("name") String name,
                         @RequestParam("surname") String surname) {
        return employeeService.find(name, surname);
    }

    @GetMapping("/")
    public List<Employee> getAll() { return employeeService.getAll(); }

    @ExceptionHandler(value = {
            IncorrectNameException.class,
            IncorrectLastNameException.class
    })

    public ResponseEntity<String> handleValidationErrors(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.TEXT_HTML)
                .body("Некорректные данные");
    }
}
