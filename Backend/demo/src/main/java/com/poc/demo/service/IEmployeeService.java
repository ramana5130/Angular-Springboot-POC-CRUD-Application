package com.poc.demo.service;

import com.poc.demo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEmployeeService {
    public List<Employee> getEmployees();

    public Employee saveEmployee(Employee employee);

    public Optional<Employee> getEmployee(Long id);

    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(Long id);
}
