package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientsMedicamentDTO;
import pl.kruko.PracaInz.models.Medicament;
import pl.kruko.PracaInz.models.PatientsMedicament;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.PatientsMedicamentRepository;

@Service
public class PatientsMedicamentService {

	@Autowired
	private VisitService visitService;
	@Autowired
	MedicamentService medicamentService;
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<PatientsMedicamentDTO>>() {
	}.getType();

	private PatientsMedicamentRepository patientsMedicamentRepository;

	@Autowired
	public PatientsMedicamentService(PatientsMedicamentRepository patientsMedicamentRepository) {
		super();
		this.patientsMedicamentRepository = patientsMedicamentRepository;
	}

	public PatientsMedicamentDTO findById(Long id) {
		PatientsMedicament patientsMedicament = patientsMedicamentRepository.findById(id)
				.orElse(new PatientsMedicament());
		PatientsMedicamentDTO patientsMedicamentDTO = modelMapper.map(patientsMedicament, PatientsMedicamentDTO.class);
		return patientsMedicamentDTO;
	}

	public List<PatientsMedicamentDTO> findByStatusAndPatient(String statusName, String login) {
		Status status = getStatus(statusName);
		List<Visit> visits = getVisit(login);
		List<PatientsMedicament> patientsMedicaments = new ArrayList<>();
		for (Visit v : visits) {
			patientsMedicaments.addAll(patientsMedicamentRepository.findByVisitAndStatus(v, status));
		}
		List<PatientsMedicamentDTO> patientsMedicamentDTO = modelMapper.map(patientsMedicaments, listType);
		return patientsMedicamentDTO;
	}

	public List<Visit> getVisit(String login) {
		List<Visit> visits = visitService.findByPatient(login);
		return visits;
	}

	public Status getStatus(String statusName) {
		Status status = Status.valueOf(statusName);
		System.out.println(status);
		return status;
	}

	public List<Medicament> getMedicaments(String medicamentName) {

		List<Medicament> medicament = medicamentService.findByName(medicamentName);
		return medicament;
	}

	public List<PatientsMedicamentDTO> findByMedicamentsAndPatient(String medicamentName, String login) {
		List<Visit> visits = getVisit(login);
		List<PatientsMedicament> patientsMedicaments = new ArrayList<>();
		List<Medicament> medicaments = getMedicaments(medicamentName);
		for (Visit v : visits) {
			for (Medicament medicament : medicaments) {
				patientsMedicaments.addAll(patientsMedicamentRepository.findByVisitAndMedicament(v, medicament));
			}
		}
		List<PatientsMedicamentDTO> patientMedicamentDTO = modelMapper.map(patientsMedicaments, listType);
		return patientMedicamentDTO;

	}

	public List<PatientsMedicamentDTO> findAllByPatient(String login) {
		List<Visit> visits = getVisit(login);
		System.out.println(visits);
		List<PatientsMedicament> patientsMedicaments = new ArrayList<>();
		for (Visit v : visits) {
			patientsMedicaments.addAll(patientsMedicamentRepository.findByVisit(v));
		}
		System.out.println(patientsMedicaments);
		List<PatientsMedicamentDTO> patientsMedicamentDTO = modelMapper.map(patientsMedicaments, listType);
		return patientsMedicamentDTO;
	}

}
