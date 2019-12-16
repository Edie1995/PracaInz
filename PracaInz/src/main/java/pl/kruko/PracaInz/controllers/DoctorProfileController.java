package pl.kruko.PracaInz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.DoctorForSearchDTO;
import pl.kruko.PracaInz.service.DoctorService;
import pl.kruko.PracaInz.service.UserService;

@Controller
public class DoctorProfileController {

	private DoctorService doctorService;
	private UserService userService;

	@Autowired
	public DoctorProfileController(DoctorService doctorService, UserService userService) {
		super();
		this.doctorService = doctorService;
		this.userService = userService;
	}

	@GetMapping("/doctorProfile.html")
	public String showAll(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		DoctorForSearchDTO doctorDTO = doctorService.findDTObyUser(login);
		model.addAttribute("doctor", doctorDTO);
		return "doctorProfile.html";

	}

	@PostMapping("/updateDoctorPassword")
	public String updateDoctorPassword(HttpServletRequest request, String userPassword) {
		String login = currentUserNameSimple(request);
		userService.updatePassword(login, userPassword);
		return "redirect:/doctorProfile.html";
	}

	@PostMapping("/updateDoctorName")
	public String updateDoctorName(HttpServletRequest request, String doctorName) {
		String login = currentUserNameSimple(request);
		doctorService.upateLastName(login, doctorName);
		return "redirect:/doctorProfile.html";
	}

	@PostMapping("/updateNumber")
	public String updateDoctorNumber(HttpServletRequest request, String doctorNumber) {
		String login = currentUserNameSimple(request);
		doctorService.updateNumber(login, doctorNumber);
		return "redirect:/doctorProfile.html";
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
