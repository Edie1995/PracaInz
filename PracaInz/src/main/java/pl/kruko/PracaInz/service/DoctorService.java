package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.VisitDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.repo.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
//	@Autowired
//	private SpecializationService specializationService;

	public DoctorService(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}

	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<DoctorDTO>>() {
	}.getType();

//	private List<DoctorDTO> findByNameAndSpecializationAndCityAndInstitution(String lastName, String specializationName, List<Institution> institutions){
//		Specialization specialization = specializationService.findByName(specializationName);
//		List<Doctor> doctors = doctorRepository.findByNameAndSpecializationAndCityAndInstitution(lastName, specialization, institutions);
//		List<DoctorDTO> doctorsDTO = modelMapper.map(doctors, listType);
//		return doctorsDTO;
//	}

	public DoctorDTO findById(Long id) {
		Doctor doctor = doctorRepository.findById(id).orElse(null);
		return modelMapper.map(doctor, DoctorDTO.class);
	}

	public List<DoctorDTO> findAll() {
		List<Doctor> doctors = doctorRepository.findAll();
		return modelMapper.map(doctors, listType);
	}
	

}
