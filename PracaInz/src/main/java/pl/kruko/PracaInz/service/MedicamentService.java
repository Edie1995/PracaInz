package pl.kruko.PracaInz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Medicament;
import pl.kruko.PracaInz.repo.MedicamentRepository;

@Service
public class MedicamentService {

	private MedicamentRepository medicamentRepository;

	@Autowired
	public MedicamentService(MedicamentRepository medicamentRepository) {
		super();
		this.medicamentRepository = medicamentRepository;
	}

//	private Medicament findById(Long id) {
//		return medicamentRepository.findById(id).orElse(new Medicament());
//	}
//
	public List<Medicament> findByName(String name) {
		return medicamentRepository.findByName(name);
	}
//
//	private List<Medicament> findAll() {
//		return medicamentRepository.findAll();
//	}
}
