package com.poc.demo.service;

import com.poc.demo.entity.Employee;
import com.poc.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private EmployeeRepository repo;

    @Override
    public List<Employee> getEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee e1 = repo.save(employee);
        return e1;
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        Optional<Employee> employee = repo.findById(id);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmp = repo.save(employee);
        return updatedEmp;
    }

    @Override
    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }
}
