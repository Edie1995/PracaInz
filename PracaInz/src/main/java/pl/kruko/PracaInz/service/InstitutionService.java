package pl.kruko.PracaInz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.repo.InstitutionRepository;

@Service
public class InstitutionService {

	@Autowired
	private InstitutionRepository institutionRepository;
	
	public Institution findById(Long id) {
		return institutionRepository.findById(id).orElse(null);
	}
}
