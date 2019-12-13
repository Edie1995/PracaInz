package pl.kruko.PracaInz.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.InstitutionDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.repo.InstitutionRepository;

@Service
public class InstitutionService {
	private ModelMapper modelMapper = new ModelMapper();
//	private Type listType = new TypeToken<List<InstitutionDTO>>() {
//	}.getType();
	@Autowired
	private InstitutionRepository institutionRepository;

	public InstitutionDTO findById(Long id) {
		Institution institution = institutionRepository.findById(id).orElse(null);
		return modelMapper.map(institution, InstitutionDTO.class);
	}

	public List<Institution> findByCityAndStatus(String city, Status status) {
		return institutionRepository.findByCityAndStatus(city, status);
	}
}
