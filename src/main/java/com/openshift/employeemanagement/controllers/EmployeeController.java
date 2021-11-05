package com.openshift.employeemanagement.controllers;

import com.openshift.employeemanagement.entities.Employee;
import com.openshift.employeemanagement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public @ResponseBody
    List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<Employee>> getEmployeesByCohort(@PathVariable String role) {
        return new ResponseEntity<>(repository.findAllByRole(role, Sort.by("name")), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody Employee getOneEmployee(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee) {
        return new ResponseEntity<>(repository.save(newEmployee), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public @ResponseBody Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updates) {
        Employee Employee = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) Employee.setName(updates.getName());
        if(updates.getRole()!= null) Employee.setRole(updates.getRole());

        return repository.save(Employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyEmployee(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
