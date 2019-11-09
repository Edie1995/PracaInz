package pl.kruko.PracaInz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Symptom;
import pl.kruko.PracaInz.repo.SymptomRepository;

@Service
public class SymptomService {

	@Autowired
	private SymptomRepository symptomRepository;
	
	
	public Symptom findById(Long id) {
		return symptomRepository.findById(id).orElse(null);
	}
	
	public Symptom findByName(String name) {
		return symptomRepository.findByName(name);
	}
	
	
}
