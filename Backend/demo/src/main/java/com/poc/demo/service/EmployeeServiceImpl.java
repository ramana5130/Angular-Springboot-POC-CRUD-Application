package com.poc.demo.service;

import com.poc.demo.entity.Employee;
import com.poc.demo.exception.EmployeeNotFoundException;
import com.poc.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

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
    public Employee findOneEmployee(Long id) {
        /*Optional<Employee> employee = repo.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException("Employee '"+ id +"'not exist");
        }*/

        return repo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee '" + id + "' not exist"));
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmp = repo.save(employee);
        return updatedEmp;
    }

    @Override
    public void deleteEmployee(Long id) {
        //repo.deleteById(id);

        repo.delete(findOneEmployee(id));
    }
}
