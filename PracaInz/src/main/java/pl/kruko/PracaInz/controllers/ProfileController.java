package pl.kruko.PracaInz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.PatientDTO;
import dataTransferObjects.UserDTO;
import pl.kruko.PracaInz.service.PatientService;
import pl.kruko.PracaInz.service.UserService;

@Controller
public class ProfileController {

	private PatientService patientService;
	private UserService userService;

	@Autowired
	public ProfileController(PatientService patientService, UserService userService) {
		super();
		this.patientService = patientService;
		this.userService = userService;
	}

	@PostMapping("/updateName")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String updatePatientName(HttpServletRequest request, String lastName) {
		String login = currentUserNameSimple(request);
		patientService.upateLastName(login, lastName);
		return "redirect:/profilePatient.html";
	}

	@PostMapping("/updateEmail")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String updatePatientMail(HttpServletRequest request, String mail) {
		String login = currentUserNameSimple(request);
		patientService.updateMail(login, mail);
		return "redirect:/profilePatient.html";
	}

	@PostMapping("/updatePassword")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String updatePatientPassword(HttpServletRequest request, String password) {
		String login = currentUserNameSimple(request);
		userService.updatePassword(login, password);

		return "redirect:/profilePatient.html";
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return login;
	}

	@GetMapping("/profilePatient.html")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String showAll(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		PatientDTO patientDTO = patientService.findDTObyUser(login);
		UserDTO user = userService.findDTOByLogin(login);
		model.addAttribute("patient", patientDTO);
		model.addAttribute("updatePatient", new PatientDTO());
		model.addAttribute("user", user);
		return "profilePatient.html";

	}

}
