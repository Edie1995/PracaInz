package pl.kruko.PracaInz.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.InstitutionDTO;
import pl.kruko.PracaInz.models.Institution;
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
}
