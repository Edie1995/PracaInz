package pl.kruko.PracaInz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/login.html")
	public String home() {
		return "login.html";
	}
	
	@RequestMapping("/login-error.html")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login.html";
	}
	
	@RequestMapping("/patientHome.html")
	public String bad() {
		return "patientHome.html";
	}
	
	@GetMapping("/bad")
	public String badOut() {
		return "bad.html";
	}
	
}
