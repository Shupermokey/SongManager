package com.sobieraj.olivia.SongManager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String homePage() {
		return "homePage";
	}
	
	@PostMapping("/login")
	public String loginPage() {
		return "loggedIn";
	}
	
	@PostMapping("/logout")
	public String loggedOut() {
		return "homePage";
	}
	
	@GetMapping("/signUp")
	public String signUp() {
		return "signUp";
	}

	
}
