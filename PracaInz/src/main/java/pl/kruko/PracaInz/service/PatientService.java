package pl.kruko.PracaInz.service;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.PatientRepository;

@Service
public class PatientService {
	private PatientRepository patientRepository;
	private Patient patient;
	@Autowired
	private UserService userService;

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
	
	public Patient findById(Long id) {
		patient = patientRepository.findById(id).orElse(new Patient());
		return patient;
	}
	
	public Patient findByUser(String login) {
		User user = userService.findByLogin(login);
		System.out.println(user);
		return patientRepository.findByUser(user);
	}
	
	public Patient findByCurrentUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return findByUser(login);
	}

}
