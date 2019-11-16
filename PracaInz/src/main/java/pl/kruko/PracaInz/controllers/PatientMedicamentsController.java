package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.PatientsMedicamentDTO;
import pl.kruko.PracaInz.service.PatientsMedicamentService;

@RestController
public class PatientMedicamentsController {

	private PatientsMedicamentService patientsMedicamentService;

	@Autowired
	public PatientMedicamentsController(PatientsMedicamentService patientsMedicamentService) {
		super();
		this.patientsMedicamentService = patientsMedicamentService;
	}

	@GetMapping("patientMedicament")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientsMedicamentDTO> showAllMedicaments(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		List<PatientsMedicamentDTO> patientMedicamentDTO = patientsMedicamentService.findAllByPatient(login);
		System.out.println(patientMedicamentDTO);
		return patientMedicamentDTO;

	}

	@GetMapping("patientMedicament/{statusName}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientsMedicamentDTO> showMedicamentsByStatus(HttpServletRequest request,
			@PathVariable String statusName) {
		String login = currentUserNameSimple(request);
		List<PatientsMedicamentDTO> patientMedicamentDTO = patientsMedicamentService.findByStatusAndPatient(statusName,
				login);
		return patientMedicamentDTO;
	}

	@GetMapping("patientMedicament/{medicamentName}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientsMedicamentDTO> showMedicamentsByName(HttpServletRequest request,
			@PathVariable String medicamentName) {
		String login = currentUserNameSimple(request);
		List<PatientsMedicamentDTO> patientMedicamentDTO = patientsMedicamentService
				.findByMedicamentsAndPatient(medicamentName, login);
		return patientMedicamentDTO;
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
