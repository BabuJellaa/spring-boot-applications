package com.tech.spring.SpringBootApplication_Hibernate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositary extends JpaRepository<Employee, Long>{}
