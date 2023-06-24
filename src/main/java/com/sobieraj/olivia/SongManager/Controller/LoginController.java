package com.sobieraj.olivia.SongManager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sobieraj.olivia.SongManager.Entity.Account;
import com.sobieraj.olivia.SongManager.Entity.ConfirmationToken;
import com.sobieraj.olivia.SongManager.Repo.AccountRepo;
import com.sobieraj.olivia.SongManager.Repo.TokenRepo;
import com.sobieraj.olivia.SongManager.Service.AccountService;
import com.sobieraj.olivia.SongManager.Service.EmailService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private AccountRepo aRepo;
	
	@Autowired
	private TokenRepo tRepo;
	
	@Autowired
	private EmailService eService;
	
	
	@PostMapping("/login")
	public String loginPage(HttpServletRequest http, Account account) {
		HttpSession session = http.getSession();
		
		if(accountService.accountPassCheck(account)){
		
			session.setAttribute("user", account.getUsername());
			return "loggedIn";
		}
		session.setAttribute("wrongUserPass", "Invalid credentials");
		return "homePage";
	}
	
	@GetMapping("/signUp")
	public String signUpPage(HttpServletRequest http, Model model, Account account) {
		HttpSession session = http.getSession();
		session.invalidate();
		model.addAttribute("registerAccount", account);
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
			
			
			
			ConfirmationToken token = new ConfirmationToken(newAccount);
			tRepo.save(token);
			
			
			
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(account.getUsername());
			mailMessage.setSubject("Complete Registration");
			mailMessage.setFrom("teemsnipers@gmail.com");
			mailMessage.setText("To confirm you account "+"http://localhost:8081/confirm-account?token="+token.getConfirmationToken());
			eService.sendEmail(mailMessage);
			

			session.setAttribute("accountSignedUp", "Account created");
		}
		else {
			session.setAttribute("accountTaken", "Account already created");
			return "signUp";
		}
		
		return "homePage";
	}

	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public String confirmUserAccount(@RequestParam("token")String token)
	{
		ConfirmationToken tok = tRepo.findByConfirmationToken(token);
		if(tok != null) {
			Account account = aRepo.findAccountByUsername(tok.getAccount().getUsername());
			account.setEnabled(true);
			aRepo.save(account);
			return "homePage";
		}
		else {
			return "music";
		}
	}
	
}
