package com.test.myproject.restwebservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.myproject.restwebservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
