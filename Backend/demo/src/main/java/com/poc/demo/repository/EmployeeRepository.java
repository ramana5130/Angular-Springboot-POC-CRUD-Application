package com.poc.demo.repository;

import com.poc.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("UPDATE Employee SET firstName=:fName WHERE id=:eId") //only @Query annotation behaves like select operation, to indicate query as non-select we use @Modifying
    int updateEmployeeFirstName(String fName, Long eId);
}
