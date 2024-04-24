package com.imaginnovate.employee.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Employee {

	private Long id;
	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private List<String> phoneNumber;
	private LocalDate dojDate;
	private Double salary;

}
