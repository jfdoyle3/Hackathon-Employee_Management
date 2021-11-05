package com.openshift.employeemanagement.repositories;

import com.openshift.employeemanagement.entities.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByRole(String role, Sort sort);

}
