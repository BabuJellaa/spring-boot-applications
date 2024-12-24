package com.tech.spring.SpringBootApplication_Hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	 
	@Autowired
	private EmployeeRepositary employeeRepositary;
	
	Employee persistUserDetails(Employee employee) {
		return employeeRepositary.save(employee);
	}
	
	 List<Employee> getAllEmployeeDetails(){
		return employeeRepositary.findAll();
	}
	
	@SuppressWarnings("deprecation")
	 Employee getEmploDetailsByEmployeeId(long employeeId) {
		return employeeRepositary.getById(employeeId);
	}
	
	 void deleteEmployeeDetails(long employeeId) {
		 employeeRepositary.deleteById(employeeId);
	}
}
