package pl.kruko.PracaInz.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientDTO;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.PatientRepository;

@Service
public class PatientService {
	private PatientRepository patientRepository;	
	private UserService userService;
	
	private ModelMapper modelMapper = new ModelMapper();

	
	@Autowired
	public PatientService(PatientRepository patientRepository, UserService userService) {
		super();
		this.patientRepository = patientRepository;
		this.userService = userService;
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

	public void upateLastName(String login, String lastName) {
		Patient patient1 = findByUser(login);
		patient1.setLastName(lastName);
		patientRepository.save(patient1);
	}

	public void updateMail(String login, String mail) {
		Patient patient1 = findByUser(login);
		patient1.setMail(mail);
		patientRepository.save(patient1);
	}

}
