package com.gigsync.syncrm.Controllers;

import com.gigsync.syncrm.Repos.EmployeeRepository;
import com.gigsync.syncrm.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    private EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Employee> findById(@PathVariable Long requestedId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(requestedId);
        if (employeeOptional.isPresent()) {
            return ResponseEntity.ok(employeeOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    private ResponseEntity<Void> createEmployee(@RequestBody Employee newEmployeeRequest, UriComponentsBuilder ucb) {
//        Employee savedEmployee = employeeRepository.save(newEmployeeRequest);
//        URI locationOfNewEmployee = ucb
//                .path("cashcards/{id}")
//                .buildAndExpand(savedEmployee.id())
//                .toUri();
//        return ResponseEntity.created(locationOfNewEmployee).build();
//    }
//
//    @GetMapping()
//    private ResponseEntity<Iterable<Employee>> findAll() {
//        return ResponseEntity.ok(employeeRepository.findAll());
//    }

}
