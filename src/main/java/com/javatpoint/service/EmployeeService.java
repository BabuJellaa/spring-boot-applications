package com.javatpoint.service;

import org.springframework.stereotype.Service;

import com.javatpoint.model.Employee;

@Service
public class EmployeeService {

	public Employee createEmployee( String empId, String fname, String sname) {
		System.out.println("Calling the createEmployee method in the EmployeeService,,,,,");
		Employee emp = new Employee();
		emp.setEmpId(empId);
		emp.setFirstName(fname);
		emp.setSecondName(sname);
		return emp;
	}

	public void deleteEmployee(String empId) {
		
	}
}