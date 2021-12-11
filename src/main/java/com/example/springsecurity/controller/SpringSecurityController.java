package com.example.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsecurity.resource.Resource;


@RestController
@RequestMapping("/books")
public class SpringSecurityController implements Resource{

	@Override
	public ResponseEntity<String> findAll() {
		return new ResponseEntity<>("from findAll", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> findById() {
		return new ResponseEntity<>("from findById", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> update() {
		return new ResponseEntity<>("from update", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> invalid() {
		return new ResponseEntity<>("from invalid", HttpStatus.OK);
	}
}
