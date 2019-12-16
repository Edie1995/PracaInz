package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.PatientSymptomDTO;
import dataTransferObjects.SymptomDTO;
import pl.kruko.PracaInz.service.PatientSymptomService;
import pl.kruko.PracaInz.service.SymptomService;

@Controller
public class PatientsSymptomsController {

	private PatientSymptomService patientSymptomService;
	private SymptomService symptomService;
	private List<PatientSymptomDTO> patientSymptomsDTO;

	@Autowired
	public PatientsSymptomsController(PatientSymptomService patientSymptomService, SymptomService symptomService) {
		super();
		this.patientSymptomService = patientSymptomService;
		this.symptomService = symptomService;
	}

	@PostMapping("patientsSymptoms/symptom")
	public String show(HttpServletRequest request, String date,
			@ModelAttribute(value = "symptomName") SymptomDTO symptom, Model model) {
		String login = currentUserNameSimple(request);
		patientSymptomsDTO = patientSymptomService.findByPatientAndSymptomAndDate(login, symptom, date);
		model.addAttribute("patientSymptoms", patientSymptomsDTO);
		return "redirect:/symptoms.html";
	}

	@GetMapping("/symptoms.html")
	public String showByPatient(HttpServletRequest request, Model model) {
		List<SymptomDTO> symptomsDictionary = symptomService.findAll();
		model.addAttribute("symptomsDic", symptomsDictionary);
		model.addAttribute("symptomName", new SymptomDTO());
		model.addAttribute("patientSymptoms", patientSymptomsDTO);
		return "symptoms.html";
	}

	@GetMapping("/homeSymptoms.html")
	public String showAll(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		patientSymptomsDTO = patientSymptomService.findByPatient(login);
		return "redirect:/symptoms.html";
	}

	@PostMapping("patientsSymptoms/add")
	public String savePatientSymptom(HttpServletRequest request,
			@ModelAttribute(value = "symptomName") SymptomDTO symptom, String date) {
		String login = currentUserNameSimple(request);
		LocalDate localDate = LocalDate.parse(date);
		patientSymptomService.savePatientSymptom(login, symptom, localDate);
		return "redirect:/homeSymptoms.html";
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
