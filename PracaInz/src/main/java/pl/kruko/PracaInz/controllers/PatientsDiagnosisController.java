package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.DiagnosisDTO;
import pl.kruko.PracaInz.service.DiagnosisService;

@Controller
public class PatientsDiagnosisController {

	private DiagnosisService diagnosisService;
	List<DiagnosisDTO> diagnosisDTO;
	List<DiagnosisDTO> diagnosisDTODic;

	@Autowired
	public PatientsDiagnosisController(DiagnosisService diagnosisService) {
		super();
		this.diagnosisService = diagnosisService;
	}

	@GetMapping("/patientDiagnosis.html")
	public String showAllDiagnosis(HttpServletRequest request, Model model) {
		model.addAttribute("diagnosisDic", diagnosisDTODic);
		model.addAttribute("diagnosis", diagnosisDTO);
		return "patientDiagnosis.html";

	}

	@GetMapping("/homePatientDiagnosis.html")
	public String showDiagnosisByName(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		diagnosisDTO = diagnosisService.findByVisit(login);
		diagnosisDTODic = diagnosisService.findByVisit(login);
		return "redirect:/patientDiagnosis.html";
	}

	@PostMapping("/patientDiagnosis.html")
	public String showMedicamentsbyStatus(HttpServletRequest request, Integer statusNumber) {
		String login = currentUserNameSimple(request);
		if (statusNumber == null) {
			return "redirect:/homePatientDiagnosis.html";
		}
		diagnosisDTO = diagnosisService.findByStatusAndPatient(statusNumber, login);
		return "redirect:/patientDiagnosis.html";

	}

	@PostMapping("/patientDiagnosis/name")
	public String showMedicamentByName(HttpServletRequest request, String diagnosisName) {
		String login = currentUserNameSimple(request);
		diagnosisDTO = diagnosisService.findByVisitAndName(login, diagnosisName);
		return "redirect:/patientDiagnosis.html";
	}

//	@GetMapping("patientDiagnosis/between/{startDate}/{endDate}")
//	@Produces(MediaType.APPLICATION_JSON_VALUE)
//	public List<DiagnosisDTO> showDiagnosisBetweenDates(HttpServletRequest request, @PathVariable String startDate,
//			@PathVariable String endDate) {
//		String login = currentUserNameSimple(request);
//		List<DiagnosisDTO> diagnosisDTO = diagnosisService.findByVisitAndDate(login, startDate, endDate);
//		System.out.println(diagnosisDTO);
//		return diagnosisDTO;
//	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return login;
	}

}
