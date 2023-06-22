package com.sobieraj.olivia.SongManager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sobieraj.olivia.SongManager.Entity.Account;
import com.sobieraj.olivia.SongManager.Repo.AccountRepo;
import com.sobieraj.olivia.SongManager.Service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@PostMapping("/login")
	public String loginPage(HttpServletRequest http, Account account) {
		HttpSession session = http.getSession();
		session.setAttribute("user", account.getUsername());
		return "loggedIn";
	}
	
	@GetMapping("/signUp")
	public String signUpPage(HttpServletRequest http) {
		HttpSession session = http.getSession();
		session.invalidate();
		return "signUp";
	}
	
	
	@PostMapping("/accountSignup")
	public String accountSignup(HttpServletRequest http, Account account) {
		HttpSession session = http.getSession();
		boolean accountExists = accountService.accountExists(account);
		
		
		if(!accountExists) {
			Account newAccount = new Account();
			newAccount.setUsername(account.getUsername());
			newAccount.setPassword(account.getPassword());
			accountService.save(newAccount);
			session.setAttribute("accountSignedUp", "Account created");
		}
		else {
			session.setAttribute("accountTaken", "Account already created");
			return "signUp";
		}
		
		

		//Need to create a check to see if Account is already created

		
		
		return "homePage";
	}

}
