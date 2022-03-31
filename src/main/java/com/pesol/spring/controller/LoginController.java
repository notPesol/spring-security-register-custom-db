package com.pesol.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/custom-login")
	public String renderLogin() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String renderAccessDenied() {
		return "access-denied";
	}
}
