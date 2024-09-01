package com.gigsync.syncrm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    protected Employee (){}
}
