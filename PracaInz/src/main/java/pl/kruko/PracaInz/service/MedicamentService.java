package pl.kruko.PracaInz.service;

import java.util.List;

import pl.kruko.PracaInz.models.Medicament;
import pl.kruko.PracaInz.repo.MedicamentRepository;

public class MedicamentService {

	private MedicamentRepository medicamentRepository;

	private Medicament findById(Long id) {
		return medicamentRepository.findById(id).orElse(new Medicament());
	}

	private List<Medicament> findByName(String name) {
		return medicamentRepository.findByName(name);
	}

	private List<Medicament> findAll() {
		return medicamentRepository.findAll();
	}
}
