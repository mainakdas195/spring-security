package com.example.springsecurity.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

public interface Resource {
	@GetMapping("/findAll")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	ResponseEntity<String> findAll();
	
	@GetMapping("{id}")
	ResponseEntity<String> findById();
	
	@GetMapping("/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<String> update();
	
	@GetMapping("/invalid")
	@PreAuthorize("hasRole('ROLE_USER')")
	ResponseEntity<String> invalid();
}
