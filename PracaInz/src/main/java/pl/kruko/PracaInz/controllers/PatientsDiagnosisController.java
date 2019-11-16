package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.DiagnosisDTO;
import pl.kruko.PracaInz.service.DiagnosisService;

@RestController
public class PatientsDiagnosisController {

	private DiagnosisService diagnosisService;


	@Autowired
	public PatientsDiagnosisController(DiagnosisService diagnosisService) {
		super();
		this.diagnosisService = diagnosisService;
	}

	@GetMapping("patientDiagnosis")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<DiagnosisDTO> showAllDiagnosis(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		List<DiagnosisDTO> diagnosisDTO = diagnosisService.findByVisit(login);
		return diagnosisDTO;

	}

	@GetMapping("patientDiagnosis/{name}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<DiagnosisDTO> showDiagnosisByName(HttpServletRequest request, @PathVariable String name) {
		String login = currentUserNameSimple(request);
		List<DiagnosisDTO> diagnosisDTO = diagnosisService.findByVisitAndName(login, name);
		return diagnosisDTO;
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
