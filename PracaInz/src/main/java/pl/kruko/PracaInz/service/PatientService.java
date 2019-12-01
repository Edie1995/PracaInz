package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientDTO;
import dataTransferObjects.PatientSymptomDTO;
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
	private Type listType = new TypeToken<List<PatientSymptomDTO>>() {
	}.getType();

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	public Patient findById(Long id) {
		Patient patient = patientRepository.findById(id).orElse(new Patient());
		return patient;
	}

	public PatientDTO findByUser(String login) {
		UserDTO userDTO = userService.findByLogin(login);
		User user = modelMapper.map(userDTO, User.class);
		System.out.println(user);
		Patient patient = patientRepository.findByUser(user);
		PatientDTO patientDto = modelMapper.map(patient, PatientDTO.class);
		return patientDto;
	}

	public PatientDTO findByCurrentUser(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return findByUser(login);
	}

	public void upateLastName(String login, PatientDTO patient) {
		PatientDTO patientDto = findByUser(login);
		patientDto.setLastName(patient.getLastName());
		Patient patient1 = modelMapper.map(patientDto, Patient.class);
		patientRepository.save(patient1);
	}

	public void updateMail(String login, PatientDTO patient) {
		PatientDTO patientDto = findByUser(login);
		patientDto.setMail(patient.getMail());
		Patient patient1 = modelMapper.map(patientDto, Patient.class);
		patientRepository.save(patient1);
	}

	public void updatePassword(String login, UserDTO userDTO) {
		UserDTO user = userService.findByLogin(login);
		user.setPassword(userDTO.getPassword());
		User user1 = modelMapper.map(user, User.class);
		userService.save(user1);
	}

}
