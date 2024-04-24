package com.imaginnovate.employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.imaginnovate.employee.model.Employee;
import com.imaginnovate.employee.service.impl.EmployeeTaxInfo;

public interface EmployeeService {

	ResponseEntity<String> addEmployee(Employee employee);

	List<EmployeeTaxInfo> calculateTaxDeduction();

}
