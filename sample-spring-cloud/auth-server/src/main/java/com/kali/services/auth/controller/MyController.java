package com.kali.services.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@RequestMapping("/hello")
	// Access control by ROLES : @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	// Access control by SCOPES : @PreAuthorize("#oauth2.hasScope('READ')")
	public String welcome_Admin() {
		return "Welcome to ADMIN";
	}

}
