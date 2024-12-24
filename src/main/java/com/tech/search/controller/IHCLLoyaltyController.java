package com.tech.search.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech.search.model.GravtyMemberEnrollmentRequest;

@RestController
public class IHCLLoyaltyController {
	
	@PostMapping("/v1/gravty/members")
	public void enrollUser(@RequestBody GravtyMemberEnrollmentRequest enrollmentRequest) {
		
	}
}
