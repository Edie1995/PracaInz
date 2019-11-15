package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.PatientSymptomDTO;
import pl.kruko.PracaInz.service.PatientSymptomService;

@RestController
@Path("hello")
public class PatientsSymptomsController {

	@Autowired
	private PatientSymptomService patientSymptomService;

	@GetMapping("patientsSymptoms/symptom/{symptomName}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptomDTO> show(HttpServletRequest request, @PathVariable String symptomName) {
		String login = currentUserNameSimple(request);
		List<PatientSymptomDTO> patientSymptomDTO = patientSymptomService.getPatientSymptomByPatientAndSymptom(login,
				symptomName);
		return patientSymptomDTO;
	}

	@GetMapping("patientsSymptoms/date/{localDate}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptomDTO> showByDate(HttpServletRequest request, @PathVariable String localDate) {
		String login = currentUserNameSimple(request);
		List<PatientSymptomDTO> patientSymptomDTO = patientSymptomService.findPatientSymptomsByPatientAndDate(login,
				LocalDate.parse(localDate));
		return patientSymptomDTO;
	}

	@GetMapping("patientsSymptoms")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptomDTO> showByPatient(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		List<PatientSymptomDTO> patientSymptomsDTO = patientSymptomService.findByPatient(login);
		return patientSymptomsDTO;
	}

	@PostMapping("patientsSymptoms/{symptomName}/{date}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public PatientSymptomDTO savePatientSymptom(HttpServletRequest request, @PathVariable String symptomName,
			@PathVariable String date) {
		String login = currentUserNameSimple(request);
		LocalDate localDate = LocalDate.parse(date);
		PatientSymptomDTO patientSymptomDTO = patientSymptomService.savePatientSymptom(login, symptomName, localDate);
		return patientSymptomDTO;
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
		List<PatientSymptomDTO> patientSymptomDTO = patientSymptomService.findByPatientAndDateBetween(login, localDateStart,
				localDateEnd);
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

}
