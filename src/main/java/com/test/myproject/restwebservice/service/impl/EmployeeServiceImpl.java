package com.test.myproject.restwebservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.myproject.restwebservice.dao.EmployeeRepository;
import com.test.myproject.restwebservice.model.Employee;
import com.test.myproject.restwebservice.model.FullTimeEmployee;
import com.test.myproject.restwebservice.model.PartTimeEmployee;
import com.test.myproject.restwebservice.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(long id) {
		Employee emp = employeeRepository.findById(id).get(); 
		if(emp instanceof PartTimeEmployee)
			System.out.println("Part time employee");
		else if(emp instanceof FullTimeEmployee)
			System.out.println("Full time employee");
		return emp;
	}

}
