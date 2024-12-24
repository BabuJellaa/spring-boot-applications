package com.tech.spring.SpringBootApplication_Hibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeCRUDController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/save")
	private Employee persistEmployeeDetails(@RequestBody Employee employee) {
		return employeeService.persistUserDetails(employee);
	}
	
	@GetMapping("/getEmployee")
	private Employee getEmployeeDetailsByID(
			@RequestParam("employeeId") long employeeId) {
		return employeeService.getEmploDetailsByEmployeeId(employeeId);
	}
	
	@GetMapping("/getAllEmployees")
	private List<Employee> getAllEmployeeDetails(){
		return employeeService.getAllEmployeeDetails();
	}
	
	@DeleteMapping("/removeEmployee")
	private void deleteEmployee(
			@RequestParam("employeeId") long employeeId) {
		employeeService.deleteEmployeeDetails(employeeId);
	}
	
	@GetMapping("/hello")
	private String greetings() {
		System.out.println("Hello Brother");
		return "Hello Brother welcome to Spring Boot - Hibernate integration";
	}
}
