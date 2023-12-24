package com.tekup.loc_voiture.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/dashboard")
	public String getAdminPage() {
		return "dashboard";
	}

	@GetMapping("/access-denied")
	public String getAccessDeniedPage() {
		return "access-denied-page";
	}
}
