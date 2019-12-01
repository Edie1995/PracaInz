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

import dataTransferObjects.PatientDTO;
import dataTransferObjects.UserDTO;
import pl.kruko.PracaInz.service.PatientService;
import pl.kruko.PracaInz.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	PatientService patientService;
	@Autowired
	UserService userService;

	PatientDTO patientDTO;
	UserDTO userDTO;

	@PostMapping("/updateName")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String updatePatientName(HttpServletRequest request, @ModelAttribute("patientDTO") PatientDTO patientDTO) {
		String login = currentUserNameSimple(request);
		System.out.println(patientDTO);
		System.out.println("lastName" + patientDTO.getLastName());
		if (!patientDTO.getLastName().isEmpty()) {
			patientService.upateLastName(login, patientDTO);
		}
		return "redirect:/profilePatient.html";
	}
	
	@PostMapping("/updateEmail")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String updatePatientMail(HttpServletRequest request, @ModelAttribute("patientDTO") PatientDTO patientDTO) {
		String login = currentUserNameSimple(request);
		System.out.println(patientDTO);
		if (!patientDTO.getMail().isEmpty()) {
			patientService.updateMail(login, patientDTO);
		}
		return "redirect:/profilePatient.html";
	}
	
	@PostMapping("/updatePassword")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String updatePatientPassword(HttpServletRequest request, @ModelAttribute("userDTO") UserDTO userDTO) {
		String login = currentUserNameSimple(request);
		System.out.println(userDTO);
		if (!userDTO.getPassword().isEmpty()) {
			patientService.updatePassword(login, userDTO);
		}
		return "redirect:/profilePatient.html";
	}
	

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}
	
	

	@GetMapping("/profilePatient.html")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String showAll(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		patientDTO = patientService.findByUser(login);
		UserDTO user = userService.findByLogin(login);
		model.addAttribute("patient", patientDTO);
		model.addAttribute("user", user);
		return "profilePatient.html";

	}
	
	
}
