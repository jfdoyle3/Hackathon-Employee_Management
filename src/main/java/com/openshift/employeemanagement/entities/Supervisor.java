package com.openshift.employeemanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supervisor extends Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Supervisor() {
    }

    public Supervisor(String name, String role) {
        super(name, role);
    }

    public Supervisor(String name, String role, String department, String location, String supervisor, Double salary) {
        super(name, role, department, location, supervisor, salary);
    }


}
