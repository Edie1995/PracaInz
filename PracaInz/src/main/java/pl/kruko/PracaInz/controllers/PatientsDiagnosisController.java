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

import pl.kruko.PracaInz.models.Diagnosis;
import pl.kruko.PracaInz.service.DiagnosisService;

@RestController
public class PatientsDiagnosisController {

	private DiagnosisService diagnosisService;

	@Autowired
	public PatientsDiagnosisController(DiagnosisService diagnosisService) {
		super();
		this.diagnosisService = diagnosisService;
	}

	@GetMapping("pateintDiagnosis")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Diagnosis> showAllDiagnosis(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		List<Diagnosis> diagnosis = diagnosisService.findByVisit(login);
		return diagnosis;

	}

	@GetMapping("pateintDiagnosis/{name}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<Diagnosis> showDiagnosisByName(HttpServletRequest request, @PathVariable String name) {
		String login = currentUserNameSimple(request);
		List<Diagnosis> diagnosis = diagnosisService.findByVisitAndName(login, name);
		return diagnosis;
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
