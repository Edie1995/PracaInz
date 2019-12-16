package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientDTO;
import dataTransferObjects.PatientSymptomDTO;
import dataTransferObjects.SymptomDTO;
import dataTransferObjects.VisitDTO;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.PatientSymptom;
import pl.kruko.PracaInz.models.Symptom;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.PatientSymptomRepository;

@Service
public class PatientSymptomService {

	private PatientSymptomRepository patientSymptomRepository;
	private PatientService patientService;
	
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<PatientSymptomDTO>>() {
	}.getType();

	@Autowired
	public PatientSymptomService(PatientSymptomRepository patientSymptomRepository, PatientService patientService) {
		super();
		this.patientSymptomRepository = patientSymptomRepository;
		this.patientService = patientService;
	}

	public PatientSymptom findById(Long id) {
		return patientSymptomRepository.findById(id).orElse(new PatientSymptom());
	}

	public List<PatientSymptomDTO> getPatientSymptomByPatientAndSymptom(String login, SymptomDTO symptomDTO) {
		Symptom symptom = getSymptom(symptomDTO);
		Patient patient = getPatient(login);
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findAllByPatientAndSymptom(patient, symptom);
		System.out.println(patientSymptoms);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);

		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findPatientSymptomsByPatientAndDate(String login, LocalDate localDate) {

		Patient patient = getPatient(login);
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findAllByPatientAndDate(patient, localDate);
		System.out.println(patientSymptoms);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findByPatient(String login) {
		Patient patient = getPatient(login);
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findAllByPatient(patient);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		Collections.sort(patientSymptomDTO);
		System.out.println(patientSymptomDTO);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findByPatientDTO(PatientDTO patientDTO) {
		Patient patient = modelMapper.map(patientDTO, Patient.class);
		;
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findAllByPatient(patient);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		Collections.sort(patientSymptomDTO);
		System.out.println(patientSymptomDTO);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findByPatientAndSymptomAndDate(String login, SymptomDTO symptomDTO, String date) {
		Patient patient = getPatient(login);
		Symptom symptom;
		if (symptomDTO.getId() != null) {
			symptom = getSymptom(symptomDTO);
		} else {
			symptom = null;
		}
		LocalDate localDate;
		if (date.isEmpty()) {
			localDate = null;
		} else {
			localDate = LocalDate.parse(date);
		}
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findAllByPatientAndSymptomAndDate(patient, symptom, localDate);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
	}

	public void savePatientSymptom(String login, SymptomDTO symptomName, LocalDate date) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		PatientSymptom patientSymptom = new PatientSymptom(date, patient, symptom);
		patientSymptomRepository.save(patientSymptom);
	}

	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

	public Symptom getSymptom(SymptomDTO symptomDTO) {
		return modelMapper.map(symptomDTO, Symptom.class);
	}

	public PatientSymptomDTO findByIdAndPatient(Long id, String login) {
		Patient patient = getPatient(login);
		PatientSymptom patientSymptom = patientSymptomRepository.findByIdAndPatient(id, patient).orElse(null);
		PatientSymptomDTO patientSymptomDTO = modelMapper.map(patientSymptom, PatientSymptomDTO.class);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findByPatientAndDateBetween(String login, LocalDate dateStart, LocalDate dateEnd) {
		Patient patient = getPatient(login);
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findByPatientAndDateBetween(patient, dateStart,
				dateEnd);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;

	}

	public void saveSymptomByVisit(SymptomDTO symptomDTO, VisitDTO visitDTO, String dateString) {
		LocalDate date = LocalDate.parse(dateString);
		Symptom symptom = modelMapper.map(symptomDTO, Symptom.class);
		Visit visit = modelMapper.map(visitDTO, Visit.class);
		System.out.println(visit.toString());
		PatientSymptom patientSymptom = new PatientSymptom(date, visit.getPatient(), symptom, visit);
		patientSymptomRepository.save(patientSymptom);

	}

	public List<PatientSymptomDTO> findByPatientAndSymptomAndDateBetween(String login, SymptomDTO symptomName,
			LocalDate dateStart, LocalDate dateEnd) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findByPatientAndSymptomAndDateBetween(patient,
				symptom, dateStart, dateEnd);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;

	}

}
