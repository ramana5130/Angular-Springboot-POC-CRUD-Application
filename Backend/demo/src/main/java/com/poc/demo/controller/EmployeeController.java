package com.poc.demo.controller;

import com.poc.demo.entity.Employee;
import com.poc.demo.exception.EmployeeNotFoundException;
import com.poc.demo.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
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


    @ApiOperation(("Creating one employee"))
    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmp(@RequestBody Employee emp) {
        Employee savedEmployee = empService.saveEmployee(emp);
        return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);
    }


    @ApiOperation("Retrieve 1 employee")
    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        /*Employee employee = empService.findOneEmployee(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);*/

        // Symbol ? is used at Generics to select datatype based on conditional execution
        ResponseEntity<?> resp = null;
        try {
            Employee employee = empService.findOneEmployee(id);
            resp = new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            // resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw e; // calls Global Handler
        }
        return resp;
    }

   /* @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id) {
        Optional<Employee> fetchedEmployee = empService.findOneEmployee(id);

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
    }*/

    @PutMapping("/modify")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
        ResponseEntity resp = null;
        try {
            empService.updateEmployee(emp);
            resp = new ResponseEntity("Employee updated", HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            throw e; // calls Global Handler
        }
        return resp;
    }

    @ApiOperation("Modify 1 field employee")
    @PatchMapping("/modify/name/{id}/{firstname}")
    public ResponseEntity<String> updateEmpFirstName(@PathVariable Long id, @PathVariable String firstname) {
        ResponseEntity resp = null;
        try {
            empService.updateEmployeeFirstName(firstname, id);
            resp = new ResponseEntity<>("Employee FirstName Updated", HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return resp;
    }


    @DeleteMapping("employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {

        ResponseEntity<String> resp = null;
        try {
            empService.deleteEmployee(id);
            resp = new ResponseEntity<String>("Employee '" + id + "'Deleted", HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            // resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw e; // calls Global Handler
        }
        return resp;
    }


}
