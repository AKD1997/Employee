package com.imaginnovate.employee.model;

import lombok.Data;

@Data
public class EmployeeTaxInfo 
{
	private Long id;
	//private Employee employee;
	private String EmployeeCode;
	private String firstName;
	private String lastName;
	private Double yearlySalary;
	private Double taxAmount;
	private Double cessAmount;

}
