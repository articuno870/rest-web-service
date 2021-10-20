package com.test.myproject.restwebservice.model;

import java.math.BigDecimal;

public class EmployeeDto {

	private String name;
	private BigDecimal salary;
	private BigDecimal hourlyWage;

	public EmployeeDto(String name, BigDecimal salary, BigDecimal hourlyWage) {
		super();
		this.name = name;
		this.salary = salary;
		this.hourlyWage = hourlyWage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

}
