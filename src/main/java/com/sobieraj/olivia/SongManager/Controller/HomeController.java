package com.sobieraj.olivia.SongManager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sobieraj.olivia.SongManager.Entity.Photo;
import com.sobieraj.olivia.SongManager.Repo.PhotoRepo;

@Controller
public class HomeController {
	
	@Autowired
	PhotoRepo pRepo;
	
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
	
	@GetMapping("/photos")
	public String photoPage(Model m) {
		List<Photo> photos = pRepo.findAll();
		m.addAttribute("photos", photos);
		return "photos";
	}
	
	@GetMapping("/music")
	public String musicPage() {
		return "music";
	}
	
	@GetMapping("/checklist")
	public String checklistPage() {
		return "checklist";
	}

	
}
