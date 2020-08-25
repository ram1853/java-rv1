package com.members.registry.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dgods")
public class LoginController {
	
	@GetMapping("/login")
	public String loginPage() {
		
		return "login-page";
	}
	
	@GetMapping("/403")
	public String authorizationFailed(Model model) {
		
		model.addAttribute("message", "You are not authorized to view this page");
		
		return "access-denied";
	}
	
}
