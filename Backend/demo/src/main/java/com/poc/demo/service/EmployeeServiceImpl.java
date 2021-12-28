package com.poc.demo.service;

import com.poc.demo.entity.Employee;
import com.poc.demo.exception.EmployeeNotFoundException;
import com.poc.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee '" + id + "' not exist"));
    }

    @Override
    public void updateEmployee(Employee employee) {
        Long id = employee.getId();
        if (id != null && repo.existsById(id)) {
            //id exist in DB then
            repo.save(employee);
        } else {
            throw new EmployeeNotFoundException("Employee '" + id + "' not exist");
        }
    }


    @Override
    public void deleteEmployee(Long id) {
        //repo.deleteById(id);

        repo.delete(findOneEmployee(id));
    }

    @Override
    @Transactional //this is custom method that's why explicitly writing @Transactional annotation
    public int updateEmployeeFirstName(String fName, Long eId) {
        if (eId != null && repo.existsById(eId)) {
            return repo.updateEmployeeFirstName(fName, eId);
        } else {
            throw new EmployeeNotFoundException("Employee '" + eId + " not exist");
        }
    }
}
