package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorForSearchDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Specialization;
import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.DoctorRepository;

@Service
public class DoctorService {

	private DoctorRepository doctorRepository;
	private SpecializationService specializationService;
	private UserService userService;
	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public DoctorService(DoctorRepository doctorRepository, SpecializationService specializationService,
			UserService userService) {
		super();
		this.doctorRepository = doctorRepository;
		this.specializationService = specializationService;
		this.userService = userService;
	}

	public List<Doctor> findByNameAndSpecialization(String lastName, String specializationName, String city) {
		Specialization specialization = specializationService.findByName(specializationName);
		List<Doctor> doctors = doctorRepository.findByNameAndSpecialization(lastName, specialization);
		return doctors;
	}
	
	public List<DoctorForSearchDTO> findAll() {
		List<Doctor> doctors = doctorRepository.findAll();
		Type listType = new TypeToken<List<DoctorForSearchDTO>>() {
		}.getType();
		return modelMapper.map(doctors, listType);
	}

	public DoctorForSearchDTO findDTObyUser(String login) {
		Doctor doctor = findByUser(login);
		return modelMapper.map(doctor, DoctorForSearchDTO.class);
	}

	public Doctor findByUser(String login) {
		User user = userService.findByLogin(login);
		Doctor doctor = user.getDoctor();
		return doctor;
	}

	public void upateLastName(String login, String doctorName) {
		Doctor doctor = findByUser(login);
		doctor.setLastName(doctorName);
		doctorRepository.save(doctor);
	}

	public void updateNumber(String login, String doctorNumber) {
		Doctor doctor = findByUser(login);
		doctor.setTelephoneNumber(Long.parseLong(doctorNumber));
		doctorRepository.save(doctor);
	}

}
