package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	@GetMapping("/get1")
	@PreAuthorize("hasAuthority('ADMIN')")
	String getData() {
		return "Hello reddy1";
	}

	@GetMapping("/get2")
	@PreAuthorize("hasAuthority('USER')")
	String getData2() {
		return "Hello reddy2";
	}

	@GetMapping("/get3")
	String getData3() {
		return "Hello reddy3";
	}

}
