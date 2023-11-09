package com.prodemy.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminDashboardController {
	
	@GetMapping("/admin")
	public String showAdminDashboard(HttpServletRequest request) {
		return "admin/dashboard";
	}

}
