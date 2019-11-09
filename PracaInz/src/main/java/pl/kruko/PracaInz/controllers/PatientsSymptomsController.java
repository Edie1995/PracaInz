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

import pl.kruko.PracaInz.models.PatientSymptom;
import pl.kruko.PracaInz.service.PatientSymptomService;

@RestController
@Path("hello")
public class PatientsSymptomsController {

	@Autowired
	private PatientSymptomService patientSymptomService;

	@GetMapping("patientsSymptoms/symptom/{symptomName}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptom> show(HttpServletRequest request, @PathVariable String symptomName) {
		String login = currentUserNameSimple(request);
		List<PatientSymptom> patientSymptom = patientSymptomService.getPatientSymptomByPatientAndSymptom(login,
				symptomName);
		return patientSymptom;
	}

	@GetMapping("patientsSymptoms/date/{localDate}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptom> showByDate(HttpServletRequest request, @PathVariable String localDate) {
		String login = currentUserNameSimple(request);
		List<PatientSymptom> patientSymptom = patientSymptomService.findPatientSymptomsByPatientAndDate(login,
				LocalDate.parse(localDate));
		return patientSymptom;
	}

	@GetMapping("patientsSymptoms")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptom> showByPatient(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		List<PatientSymptom> patientSymptoms = patientSymptomService.findByPatient(login);
		return patientSymptoms;
	}

	@PostMapping("patientsSymptoms/{symptomName}/{date}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public PatientSymptom savePatientSymptom(HttpServletRequest request,@PathVariable String symptomName, @PathVariable String date) {
		String login = currentUserNameSimple(request);
		LocalDate localDate = LocalDate.parse(date);
		PatientSymptom patientSymptom = patientSymptomService.savePatientSymptom(login,symptomName, localDate);		
		return patientSymptom;
	}

	@DeleteMapping("patientsSymptoms/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public PatientSymptom deletePatientSymptom(HttpServletRequest request, @PathVariable Long id) {
		String login = currentUserNameSimple(request);
		PatientSymptom patientSymptom = patientSymptomService.findByIdAndPatient(id, login);
		patientSymptomService.deletePatientSymptom(patientSymptom);
		return patientSymptom;
	}
	
	@GetMapping ("patientsSymptoms/between/{dateStart}/{dateEnd}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptom> showBetweenDates(HttpServletRequest request, @PathVariable String dateStart, @PathVariable String dateEnd){
		String login = currentUserNameSimple(request);
		LocalDate localDateStart = LocalDate.parse(dateStart);
		LocalDate localDateEnd = LocalDate.parse(dateEnd);
		return patientSymptomService.findByPatientAndDateBetween(login, localDateStart, localDateEnd);
	}
	
	@GetMapping ("patientsSymptoms/{symptomName}/between/{dateStart}/{dateEnd}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<PatientSymptom> showSymptomBetweenDates(HttpServletRequest request,@PathVariable String symptomName, @PathVariable String dateStart, @PathVariable String dateEnd){
		String login = currentUserNameSimple(request);
		LocalDate localDateStart = LocalDate.parse(dateStart);
		LocalDate localDateEnd = LocalDate.parse(dateEnd);
		return patientSymptomService.findByPatientAndSymptomAndDateBetween(login, symptomName, localDateStart, localDateEnd);
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
