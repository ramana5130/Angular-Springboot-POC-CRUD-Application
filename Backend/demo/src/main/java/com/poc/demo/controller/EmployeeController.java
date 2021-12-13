package com.poc.demo.controller;

import com.poc.demo.entity.Employee;
import com.poc.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app/v1")
public class EmployeeController {

    @Autowired
    private IEmployeeService empService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = empService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmp(@RequestBody Employee emp) {
        Employee savedEmployee = empService.saveEmployee(emp);
        return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = empService.getEmployee(id);
        return new ResponseEntity<Optional<Employee>>(employee, HttpStatus.OK);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        Optional<Employee> fetchedEmployee = empService.getEmployee(id);

        if (fetchedEmployee.isPresent()) {
            Employee emp = fetchedEmployee.get();

            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setGender(employee.getGender());
            emp.setEmail(employee.getEmail());
            emp.setMobile(employee.getMobile());

            return new ResponseEntity<Employee>(empService.updateEmployee(emp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable ("id") Long id){
        empService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
