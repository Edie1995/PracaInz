package pl.kruko.PracaInz.controllers;

import javax.servlet.http.HttpServletRequest;

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

	@GetMapping("/home.html")
	public String bad(HttpServletRequest request) {
		if (request.isUserInRole("PATIENT")) {
			return "patientHome.html";
		} else if (request.isUserInRole("DOCTOR")) {
			return "doctorHome.html";
		} else {
			return "/login-error.html";
		}
	}

//	@RequestMapping("/patientHome.html")
//	public String patientHome() {
//		return "patientHome.html";
//	}
//
//	@RequestMapping("/doctorHome.html")
//	public String doctorHome() {
//		return "doctorHome.html";
//	}

}
