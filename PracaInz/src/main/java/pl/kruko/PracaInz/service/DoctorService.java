package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.PatientDTO;
import dataTransferObjects.UserDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Specialization;
import pl.kruko.PracaInz.models.User;
import pl.kruko.PracaInz.repo.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private SpecializationService specializationService;
	@Autowired
	private UserService userService;

	public DoctorService(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}

	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<DoctorDTO>>() {
	}.getType();

	public List<Doctor> findByNameAndSpecialization(String lastName, String specializationName, String city) {
		Specialization specialization = specializationService.findByName(specializationName);
		List<Doctor> doctors = doctorRepository.findByNameAndSpecialization(lastName, specialization);
		return doctors;
	}

	public DoctorDTO findById(Long id) {
		Doctor doctor = doctorRepository.findById(id).orElse(null);
		return modelMapper.map(doctor, DoctorDTO.class);
	}

	public List<DoctorDTO> findAll() {
		List<Doctor> doctors = doctorRepository.findAll();
		return modelMapper.map(doctors, listType);
	}

	public void updatePassword(String login, UserDTO userDTO) {
		User user = userService.findByLogin(login);
		user.setPassword(userDTO.getPassword());
		userService.save(user);
	}

	public DoctorDTO findDTObyUser(String login) {
		Doctor doctor = findByUser(login);
		return modelMapper.map(doctor, DoctorDTO.class);
	}

	public Doctor findByUser(String login) {
		User user = userService.findByLogin(login);
		Doctor doctor = user.getDoctor();
		return doctor;
	}

	public void upateLastName(String login, DoctorDTO doctorDTO) {
		Doctor doctor = findByUser(login);
		doctor.setLastName(doctorDTO.getLastName());
		doctorRepository.save(doctor);
	}

	public void updateNumber(String login, DoctorDTO doctorDTO) {
		Doctor doctor = findByUser(login);
		doctor.setTelephoneNumber(doctorDTO.getTelephoneNumber());
		doctorRepository.save(doctor);
	}

}
