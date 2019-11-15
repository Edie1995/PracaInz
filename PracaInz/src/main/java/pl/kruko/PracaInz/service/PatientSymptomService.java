package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.PatientSymptomDTO;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.PatientSymptom;
import pl.kruko.PracaInz.models.Symptom;
import pl.kruko.PracaInz.repo.PatientSymptomRepository;

@Service
public class PatientSymptomService {

	private PatientSymptomRepository patientSymptomRepository;
	List<PatientSymptom> patientSymptoms;
	@Autowired
	PatientService patientService;
	@Autowired
	SymptomService symptomService;
	
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<PatientSymptomDTO>>() {
	}.getType();

	@Autowired
	public PatientSymptomService(PatientSymptomRepository patientSymptomRepository) {
		super();
		this.patientSymptomRepository = patientSymptomRepository;
	}

	public PatientSymptomService() {
	}

	public PatientSymptom findById(Long id) {
		return patientSymptomRepository.findById(id).orElse(new PatientSymptom());
	}

	public List<PatientSymptomDTO> getPatientSymptomByPatientAndSymptom(String login, String symptomName) {
		Symptom symptom = getSymptom(symptomName);
		Patient patient = getPatient(login);
		patientSymptoms = patientSymptomRepository.findAllByPatientAndSymptom(patient, symptom);
		System.out.println(patientSymptoms);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findPatientSymptomsByPatientAndDate(String login, LocalDate localDate) {

		Patient patient = getPatient(login);
		patientSymptoms = patientSymptomRepository.findAllByPatientAndDate(patient, localDate);
		System.out.println(patientSymptoms);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findByPatient(String login) {
		Patient patient = getPatient(login);
		patientSymptoms = patientSymptomRepository.findAllByPatient(patient);
		System.out.println(patientSymptoms);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
	}

	public List<PatientSymptomDTO> findByPatientAndSymptomAndDate(String login, String symptomName, LocalDate date) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		patientSymptoms = patientSymptomRepository.findAllByPatientAndSymptomAndDate(patient, symptom, date);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
	}

	public PatientSymptomDTO savePatientSymptom(String login, String symptomName, LocalDate date) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		PatientSymptom patientSymptom = new PatientSymptom(date, patient, symptom);
		patientSymptomRepository.save(patientSymptom);
		return modelMapper.map(patientSymptom, PatientSymptomDTO.class);
	}

	public void deletePatientSymptom(PatientSymptomDTO patientSymptomDTO) {
		PatientSymptom patientSymptom = modelMapper.map(patientSymptomDTO,PatientSymptom.class);
		patientSymptomRepository.delete(patientSymptom);
	}

	public Patient currentPatient(HttpServletRequest request) {
		Patient patient = patientService.findByCurrentUser(request);
		return patient;
	}
	
	public Patient getPatient(String login) {
		return patientService.findByUser(login);
	}
	
	public Symptom getSymptom(String symptomName) {
		return symptomService.findByName(symptomName);
	}
	
	public PatientSymptomDTO findByIdAndPatient(Long id, String login) {
		Patient patient = getPatient(login);
		PatientSymptom patientSymptom = patientSymptomRepository.findByIdAndPatient(id, patient).orElse(null);
		PatientSymptomDTO patientSymptomDTO = modelMapper.map(patientSymptom, PatientSymptomDTO.class);
		return patientSymptomDTO;
	}
	
	public List<PatientSymptomDTO> findByPatientAndDateBetween(String login, LocalDate dateStart, LocalDate dateEnd) {
		Patient patient = getPatient(login);
		List<PatientSymptom> patientSymptoms =  patientSymptomRepository.findByPatientAndDateBetween(patient, dateStart, dateEnd);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
		
	}
	
	public List<PatientSymptomDTO> findByPatientAndSymptomAndDateBetween(String login, String symptomName, LocalDate dateStart, LocalDate dateEnd) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		List<PatientSymptom> patientSymptoms = patientSymptomRepository.findByPatientAndSymptomAndDateBetween(patient, symptom, dateStart, dateEnd);
		List<PatientSymptomDTO> patientSymptomDTO = modelMapper.map(patientSymptoms, listType);
		return patientSymptomDTO;
		
	}

}
