package com.test.myproject.restwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.test.myproject.restwebservice.model.Employee;
import com.test.myproject.restwebservice.model.EmployeeDto;
import com.test.myproject.restwebservice.model.FullTimeEmployee;
import com.test.myproject.restwebservice.model.PartTimeEmployee;
import com.test.myproject.restwebservice.service.EmployeeService;

/**
 * this flow is for inheritance strategy
 * 
 * @author abhis
 *
 */
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping(path = "/employee")
	public Employee saveEmployee(@RequestBody EmployeeDto employee, @RequestHeader String type) {
		Employee emp = null;
		if (type.equals("partTime")) {
			emp = new PartTimeEmployee(employee.getName(), employee.getHourlyWage());
		} else if (type.equals("fullTime")) {
			emp = new FullTimeEmployee(employee.getName(), employee.getSalary());
		}
		return employeeService.saveEmployee(emp);
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable long id) {
		return employeeService.getEmployee(id);
	}

}
