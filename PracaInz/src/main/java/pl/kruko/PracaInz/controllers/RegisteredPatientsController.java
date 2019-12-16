package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dataTransferObjects.ScheduledVisitDTO;
import pl.kruko.PracaInz.service.DoctorService;
import pl.kruko.PracaInz.service.ScheduledVisitService;
import pl.kruko.PracaInz.service.UserService;

@Controller
public class RegisteredPatientsController {
	@Autowired
	DoctorService doctorService;
	@Autowired
	UserService userService;
	@Autowired
	ScheduledVisitService scheduledVisitService;

	List<ScheduledVisitDTO> visits;
	LocalDate chosenDate;

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return login;
	}

	@GetMapping("/registeredPatients.html")
	public String getRegisteredPatients(HttpServletRequest request, Model model) {
		model.addAttribute("visits", visits);
		model.addAttribute("chosenDate", chosenDate);
		return "registeredPatients.html";
	}

	@GetMapping("/homeRegisteredPatient.html")
	public String getHomeRegisteredPatients(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		chosenDate = LocalDate.now();
		visits = scheduledVisitService.findByDoctorAndDate(login, chosenDate);
		return "redirect:/registeredPatients.html";
	}

	@PostMapping("/registeredPatients/search")
	public String getDateRegisteredPatients(HttpServletRequest request, String date, Model model) {
		String login = currentUserNameSimple(request);
		chosenDate = LocalDate.parse(date);
		visits = scheduledVisitService.findByDoctorAndDate(login, chosenDate);
		return "redirect:/registeredPatients.html";
	}

	@PostMapping("/registeredPatients/getPatient")
	public String getPatientVisit(HttpServletRequest request, int docorIdx, RedirectAttributes redirectAttributes) {
		ScheduledVisitDTO visit = visits.get(docorIdx);
		redirectAttributes.addFlashAttribute("visit", visit);
		return "redirect:/homechosenPatient.html";
	}
	
	
}
