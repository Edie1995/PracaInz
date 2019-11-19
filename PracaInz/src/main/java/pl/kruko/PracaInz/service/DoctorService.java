package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import dataTransferObjects.DoctorDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Specialization;
import pl.kruko.PracaInz.repo.DoctorRepository;

public class DoctorService {
	
	private DoctorRepository doctorRepository;
	private SpecializationService specializationService;
	
	public DoctorService(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<DoctorDTO>>() {
	}.getType();

	
	private List<DoctorDTO> findByNameAndSpecializationAndCityAndInstitution(String lastName, String specializationName, List<Institution> institutions){
		Specialization specialization = specializationService.findByName(specializationName);
		List<Doctor> doctors = doctorRepository.findByNameAndSpecializationAndCityAndInstitution(lastName, specialization, institutions);
		List<DoctorDTO> doctorsDTO = modelMapper.map(doctors, listType);
		return doctorsDTO;
	}
	
	
	
}
