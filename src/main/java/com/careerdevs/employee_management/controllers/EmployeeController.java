package com.careerdevs.employee_management.controllers;
import com.careerdevs.employee_management.entities.Employee;
import com.careerdevs.employee_management.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    private AtomicLong idCounter = new AtomicLong();


    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    Map<Long, Employee> employees = new HashMap<>();



    @GetMapping
    public List<Employee> all() {
        return new ArrayList<Employee>(employees.values());
    }

    @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        Long id = idCounter.incrementAndGet();
        newEmployee.setId(id);

        employees.put(id, newEmployee);

        return newEmployee;

    }

    @GetMapping("/{id}")
    public Employee employee(@PathVariable Long id)  {
        return employees.get(id);
    }


}
