package pl.kruko.PracaInz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.repo.InstitutionRepository;

@Service
public class InstitutionService {
	private InstitutionRepository institutionRepository;

	@Autowired
	public InstitutionService(InstitutionRepository institutionRepository) {
		super();
		this.institutionRepository = institutionRepository;
	}

	public List<Institution> findByCityAndStatus(String city, Status status) {
		return institutionRepository.findByCityAndStatus(city, status);
	}
}
