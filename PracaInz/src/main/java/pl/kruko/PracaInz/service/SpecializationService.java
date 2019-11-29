package pl.kruko.PracaInz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Specialization;
import pl.kruko.PracaInz.repo.SpecializationRepository;

@Service
public class SpecializationService {

	private SpecializationRepository specializationRepository;

	@Autowired
	public SpecializationService(SpecializationRepository specializationRepository) {
		super();
		this.specializationRepository = specializationRepository;
	}

	public Specialization findByName(String name) {
		Specialization specialization = specializationRepository.findByName(name);
		return specialization;
	}

}
