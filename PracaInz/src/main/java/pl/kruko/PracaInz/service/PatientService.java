package pl.kruko.PracaInz.service;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientDTO;
import dataTransferObjects.UserDTO;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.PatientRepository;

@Service
public class PatientService {
	private PatientRepository patientRepository;
	@Autowired
	private UserService userService;
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	public Patient findById(Long id) {
		Patient patient = patientRepository.findById(id).orElse(new Patient());
		return patient;
	}

	public Patient findByUser(String login) {
		User user = userService.findByLogin(login);
		Patient patient = patientRepository.findByUser(user);
		return patient;
	}

	public PatientDTO findDTObyUser(String login) {
		Patient patient = findByUser(login);
		return modelMapper.map(patient, PatientDTO.class);
	}

	public Patient findByCurrentUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return findByUser(login);
	}

	public void upateLastName(String login, PatientDTO patient) {
		Patient patient1 = findByUser(login);
		patient1.setLastName(patient.getLastName());
//		Patient patient1 = modelMapper.map(patientDto, Patient.class);
		patientRepository.save(patient1);
	}

	public void updateMail(String login, PatientDTO patient) {
		Patient patient1 = findByUser(login);
		patient1.setMail(patient.getMail());
//		Patient patient1 = modelMapper.map(patient, Patient.class);
		patientRepository.save(patient1);
	}

	public void updatePassword(String login, UserDTO userDTO) {
		User user = userService.findByLogin(login);
		user.setPassword(userDTO.getPassword());
//		User user1 = modelMapper.map(user, User.class);
		userService.save(user);
	}

}
