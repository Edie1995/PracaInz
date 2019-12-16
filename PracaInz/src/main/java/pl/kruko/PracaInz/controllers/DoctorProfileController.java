package pl.kruko.PracaInz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.UserDTO;
import pl.kruko.PracaInz.service.DoctorService;
import pl.kruko.PracaInz.service.UserService;

@Controller
public class DoctorProfileController {
	@Autowired
	DoctorService doctorService;
	@Autowired
	UserService userService;

	DoctorDTO doctorDTO;
	UserDTO userDTO;

	@GetMapping("/doctorProfile.html")
	public String showAll(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		doctorDTO = doctorService.findDTObyUser(login);
		UserDTO user = userService.findDTOByLogin(login);
		model.addAttribute("doctor", doctorDTO);
		model.addAttribute("updateDoctor", new DoctorDTO());
		model.addAttribute("user", user);
		return "doctorProfile.html";

	}

	@PostMapping("/updateDoctorPassword")
	public String updateDoctorPassword(HttpServletRequest request, @ModelAttribute("userDTO") UserDTO userDTO) {
		String login = currentUserNameSimple(request);
		doctorService.updatePassword(login, userDTO);
		return "redirect:/doctorProfile.html";
	}

	@PostMapping("/updateDoctorName")
	public String updateDoctorName(HttpServletRequest request, @ModelAttribute("doctorDTO") DoctorDTO doctorDTO) {
		String login = currentUserNameSimple(request);
		doctorService.upateLastName(login, doctorDTO);
		return "redirect:/doctorProfile.html";
	}

	@PostMapping("/updateNumber")
	public String updateDoctorNumber(HttpServletRequest request, @ModelAttribute("doctorDTO") DoctorDTO doctorDTO) {
		String login = currentUserNameSimple(request);
		doctorService.updateNumber(login, doctorDTO);
		return "redirect:/doctorProfile.html";
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}
	
	

}
