package com.imaginnovate.employee.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imaginnovate.employee.model.Employee;
import com.imaginnovate.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final List<Employee> employees = new ArrayList<>();

	@Override
	public ResponseEntity<String> addEmployee(Employee employee) 
	{
	    if (isValidEmployee(employee)) 
	    {
	        employees.add(employee); // Add the employee to the list
	        return ResponseEntity.ok("Employee added successfully");
	    } else 
	    {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee data");
	    }
	}
	
	@Override
	public List<EmployeeTaxInfo> calculateTaxDeduction() 
	{
		Long count = 0L;
        List<EmployeeTaxInfo> taxInfoList = new ArrayList<>();
        for (Employee employee : employees) 
        {
        	count = count+1L;
        	EmployeeTaxInfo taxInfo = new EmployeeTaxInfo();
            Double yearlySalary = calculateYearlySalary(employee);
            Double taxAmount = calculateTaxAmount(yearlySalary);
            Double cessAmount = calculateCessAmount(yearlySalary);
            taxInfo.setId(count); 
            //taxInfo.setEmployee(employee); 
            taxInfo.setEmployeeCode(employee.getEmployeeId());
            taxInfo.setFirstName(employee.getFirstName());
            taxInfo.setLastName(employee.getLastName());
            taxInfo.setYearlySalary(yearlySalary);
            taxInfo.setTaxAmount(taxAmount);
            taxInfo.setCessAmount(cessAmount);
            taxInfoList.add(taxInfo);
        }
        return taxInfoList;
    }
	
	private Double calculateYearlySalary(Employee employee) 
	{
		Double monthlySalary = employee.getSalary();
	    LocalDate dojDate = employee.getDojDate();
	    LocalDate currentDate = LocalDate.now();
	    
	    // Calculate difference with current date
	    Period period = Period.between(dojDate, currentDate);
	    // Calculate total months of service
	    Integer monthsOfService = period.getYears() * 12 + period.getMonths();

	    // Check if DOJ is in the current financial year (April to March)
	    Boolean isCurrentYear = (dojDate.getMonthValue() >= 4 && dojDate.getYear() == currentDate.getYear()) ||
	                            (dojDate.getMonthValue() < 4 && dojDate.getYear() == currentDate.getYear() - 1);

	    // Calculate yearly salary if DOJ is in the current financial year
	    Double yearlySalary = isCurrentYear ? monthlySalary * monthsOfService / 12.0 : 0;
	    
	    return yearlySalary;
    }

	private Double calculateTaxAmount(double yearlySalary) 
	{
	    if (yearlySalary <= 250000) 
	    {
	        return 0.0; // No tax for yearly salary <= 250000
	    } else if (yearlySalary <= 500000) 
	    {
	        
	        return (yearlySalary - 250000) * 0.05; // 5% Tax for yearly salary > 250000 and <= 500000
	    } else if (yearlySalary <= 1000000) 
	    {
	        
	        return 12500 + (yearlySalary - 500000) * 0.10; // 10% Tax for yearly salary > 500000 and <= 1000000
	    } else 
	    {
	        return 112500 + (yearlySalary - 1000000) * 0.20; // 20% Tax for yearly salary > 1000000
	    }
	}


	private Double calculateCessAmount(Double yearlySalary) 
	{
	    return yearlySalary > 2500000 ? (yearlySalary - 2500000) * 0.02 : 0;
	}



	// Employee validation logic
	private boolean isValidEmployee(Employee employee) 
	{
	    return employee != null;
	}
}
