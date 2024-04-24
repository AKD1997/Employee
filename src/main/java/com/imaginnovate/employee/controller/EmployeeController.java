package com.imaginnovate.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.employee.model.Employee;
import com.imaginnovate.employee.model.EmployeeTaxInfo;
import com.imaginnovate.employee.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/add-employees")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) 
	{
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/tax-deduction")
    public ResponseEntity<List<EmployeeTaxInfo>> getTaxDeduction() 
	{
        return ResponseEntity.ok(employeeService.calculateTaxDeduction());
    }
}
