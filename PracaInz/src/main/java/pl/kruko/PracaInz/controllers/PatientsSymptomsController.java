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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.PatientSymptomDTO;
import dataTransferObjects.SymptomDTO;
import pl.kruko.PracaInz.service.PatientSymptomService;
import pl.kruko.PracaInz.service.SymptomService;

@Controller
public class PatientsSymptomsController {

	@Autowired
	private PatientSymptomService patientSymptomService;
	@Autowired
	private SymptomService symptomService;

	private List<PatientSymptomDTO> patientSymptomsDTO;

//	@GetMapping("patientsSymptoms/symptom")
//	@Produces(MediaType.APPLICATION_JSON_VALUE)
//	public String show(HttpServletRequest request,String symptomName, String date, Model model) {
//		System.out.println(date);		
//		String login = currentUserNameSimple(request);
//		LocalDate localDate;
//		if(symptomName.isEmpty()&date.isEmpty()){
//			return "redirect:/symptoms.html";
//		}
//		else if(symptomName.isEmpty()) {
//			localDate = LocalDate.parse(date);
//			patientSymptomsDTO = patientSymptomService.findPatientSymptomsByPatientAndDate(login,
//					localDate);
//		}else if (date.isEmpty()) {
//			patientSymptomsDTO = patientSymptomService.getPatientSymptomByPatientAndSymptom(login,
//					symptomName);
//		}else{
//			localDate = LocalDate.parse(date);
//			patientSymptomsDTO = patientSymptomService.findByPatientAndSymptomAndDate(login, symptomName, localDate);
//		}
//		model.addAttribute("patientSymptoms", patientSymptomsDTO);	
//		return "redirect:/symptoms.html";
//	}

	@PostMapping("patientsSymptoms/symptom")
	public String show(HttpServletRequest request, String date, @ModelAttribute(value="symptomName") SymptomDTO symptomName,
			Model model) {
		String login = currentUserNameSimple(request);
		LocalDate localDate;
		if (symptomName.getId()==null & date.isEmpty()) {
			return "redirect:/symptoms.html";
		} else if (symptomName.getId()==null) {
			localDate = LocalDate.parse(date);
			patientSymptomsDTO = patientSymptomService.findPatientSymptomsByPatientAndDate(login, localDate);
		} else if (date.isEmpty()) {
			patientSymptomsDTO = patientSymptomService.getPatientSymptomByPatientAndSymptom(login, symptomName);
		} else {
			localDate = LocalDate.parse(date);
			patientSymptomsDTO = patientSymptomService.findByPatientAndSymptomAndDate(login, symptomName, localDate);
		}
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
	public String showAll(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		patientSymptomsDTO = patientSymptomService.findByPatient(login);
		model.addAttribute("patientSymptoms", patientSymptomsDTO);
		return "redirect:/symptoms.html";
	}

	@PostMapping("patientsSymptoms/add")
	public String savePatientSymptom(HttpServletRequest request, @ModelAttribute(value="symptomName") SymptomDTO symptomName, String date) {
		String login = currentUserNameSimple(request);
		LocalDate localDate = LocalDate.parse(date);
		patientSymptomService.savePatientSymptom(login, symptomName, localDate);
		return "redirect:/homeSymptoms.html";
	}

//	@DeleteMapping("patientsSymptoms/{id}")
//	public PatientSymptomDTO deletePatientSymptom(HttpServletRequest request, @PathVariable Long id) {
//		String login = currentUserNameSimple(request);
//		PatientSymptomDTO patientSymptomDTO = patientSymptomService.findByIdAndPatient(id, login);
//		patientSymptomService.deletePatientSymptom(patientSymptomDTO);
//		return patientSymptomDTO;
//	}

	@GetMapping("patientsSymptoms/between/{dateStart}/{dateEnd}")
	public List<PatientSymptomDTO> showBetweenDates(HttpServletRequest request, @PathVariable String dateStart,
			@PathVariable String dateEnd) {
		String login = currentUserNameSimple(request);
		LocalDate localDateStart = LocalDate.parse(dateStart);
		LocalDate localDateEnd = LocalDate.parse(dateEnd);
		List<PatientSymptomDTO> patientSymptomDTO = patientSymptomService.findByPatientAndDateBetween(login,
				localDateStart, localDateEnd);
		return patientSymptomDTO;
	}

	@GetMapping("patientsSymptoms/{symptomName}/between/{dateStart}/{dateEnd}")
	public List<PatientSymptomDTO> showSymptomBetweenDates(HttpServletRequest request, @PathVariable SymptomDTO symptomName,
			@PathVariable String dateStart, @PathVariable String dateEnd) {
		String login = currentUserNameSimple(request);
		LocalDate localDateStart = LocalDate.parse(dateStart);
		LocalDate localDateEnd = LocalDate.parse(dateEnd);
		List<PatientSymptomDTO> patientSymptomDTO = patientSymptomService.findByPatientAndSymptomAndDateBetween(login,
				symptomName, localDateStart, localDateEnd);
		return patientSymptomDTO;

	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
