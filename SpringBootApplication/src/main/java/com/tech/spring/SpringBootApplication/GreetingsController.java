package com.tech.spring.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {
	
	@GetMapping
	public String greetingFunction() {
		System.out.println("Spring boot welcome text displayed in UI");
		return "Welcome to SpringBoot..!!";
	}
}
