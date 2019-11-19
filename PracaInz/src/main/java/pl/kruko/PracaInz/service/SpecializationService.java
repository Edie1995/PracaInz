package pl.kruko.PracaInz.service;

import org.springframework.beans.factory.annotation.Autowired;

import pl.kruko.PracaInz.models.Specialization;
import pl.kruko.PracaInz.repo.SpecializationRepository;

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
