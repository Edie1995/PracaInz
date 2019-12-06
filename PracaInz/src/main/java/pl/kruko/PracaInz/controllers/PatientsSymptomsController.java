package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("patientsSymptoms/symptom")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String show(HttpServletRequest request,String symptomName, String date, Model model) {
		System.out.println(date);		
		String login = currentUserNameSimple(request);
		LocalDate localDate;
		if(symptomName.isEmpty()&date.isEmpty()){
			return "redirect:/symptoms.html";
		}
		else if(symptomName.isEmpty()) {
			localDate = LocalDate.parse(date);
			patientSymptomsDTO = patientSymptomService.findPatientSymptomsByPatientAndDate(login,
					localDate);
		}else if (date.isEmpty()) {
			patientSymptomsDTO = patientSymptomService.getPatientSymptomByPatientAndSymptom(login,
					symptomName);
		}else{
			localDate = LocalDate.parse(date);
			patientSymptomsDTO = patientSymptomService.findByPatientAndSymptomAndDate(login, symptomName, localDate);
		}
		model.addAttribute("patientSymptoms", patientSymptomsDTO);	
		return "redirect:/symptoms.html";
	}

	@GetMapping("patientsSymptoms/date/{localDate}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptomDTO> showByDate(HttpServletRequest request, @PathVariable String localDate) {
		String login = currentUserNameSimple(request);
		List<PatientSymptomDTO> patientSymptomDTO = patientSymptomService.findPatientSymptomsByPatientAndDate(login,
				LocalDate.parse(localDate));
		return patientSymptomDTO;
	}

	@GetMapping("/symptoms.html")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String showByPatient(HttpServletRequest request, Model model) {
		List<SymptomDTO> symptomsDictionary = symptomService.findAll();
		model.addAttribute("symptomsDic", symptomsDictionary);
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
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public String savePatientSymptom(HttpServletRequest request, String symptomName,
			String date) {
		String login = currentUserNameSimple(request);
		LocalDate localDate = LocalDate.parse(date);
		patientSymptomService.savePatientSymptom(login, symptomName, localDate);
		return "redirect:/homeSymptoms.html";
	}
	
	@DeleteMapping("patientsSymptoms/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public PatientSymptomDTO deletePatientSymptom(HttpServletRequest request, @PathVariable Long id) {
		String login = currentUserNameSimple(request);
		PatientSymptomDTO patientSymptomDTO = patientSymptomService.findByIdAndPatient(id, login);
		patientSymptomService.deletePatientSymptom(patientSymptomDTO);
		return patientSymptomDTO;
	}

	@GetMapping("patientsSymptoms/between/{dateStart}/{dateEnd}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
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
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptomDTO> showSymptomBetweenDates(HttpServletRequest request, @PathVariable String symptomName,
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

//	@GetMapping("/symptoms.html")
//	public String badOut() {
//		
//		return "symptoms.html";
//	}

}
