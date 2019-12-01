package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientSymptomDTO;
import dataTransferObjects.SymptomDTO;
import pl.kruko.PracaInz.models.Symptom;
import pl.kruko.PracaInz.repo.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	private SymptomRepository symptomRepository;
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<SymptomDTO>>() {
	}.getType();
	
	public Symptom findById(Long id) {
		return symptomRepository.findById(id).orElse(null);
	}
	
	public Symptom findByName(String name) {
		return symptomRepository.findByName(name);
	}
	public List<SymptomDTO> findAll(){
		List<Symptom> symptoms = symptomRepository.findAll();
		return modelMapper.map(symptoms, listType);
	}
	
}
