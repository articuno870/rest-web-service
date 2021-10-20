package com.test.myproject.restwebservice.service;

import com.test.myproject.restwebservice.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public Employee getEmployee(long id);

}
