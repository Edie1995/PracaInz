package pl.kruko.PracaInz.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<PatientSymptom> getPatientSymptomByPatientAndSymptom(String login, String symptomName) {
		Symptom symptom = getSymptom(symptomName);
		Patient patient = getPatient(login);
		patientSymptoms = patientSymptomRepository.findAllByPatientAndSymptom(patient, symptom);
		System.out.println(patientSymptoms);
		return patientSymptoms;
	}

	public List<PatientSymptom> findPatientSymptomsByPatientAndDate(String login, LocalDate localDate) {

		Patient patient = getPatient(login);
		patientSymptoms = patientSymptomRepository.findAllByPatientAndDate(patient, localDate);
		System.out.println(patientSymptoms);
		return patientSymptoms;
	}

	public List<PatientSymptom> findByPatient(String login) {
		Patient patient = getPatient(login);
		patientSymptoms = patientSymptomRepository.findAllByPatient(patient);
		System.out.println(patientSymptoms);
		return patientSymptoms;
	}

	public List<PatientSymptom> findByPatientAndSymptomAndDate(String login, String symptomName, LocalDate date) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		return patientSymptomRepository.findAllByPatientAndSymptomAndDate(patient, symptom, date);
	}

	public PatientSymptom savePatientSymptom(String login, String symptomName, LocalDate date) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		PatientSymptom patientSymptom = new PatientSymptom(date, patient, symptom);
		return patientSymptomRepository.save(patientSymptom);
	}

	public void deletePatientSymptom(PatientSymptom patientSymptom) {
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
	
	public PatientSymptom findByIdAndPatient(Long id, String login) {
		Patient patient = getPatient(login);
		return patientSymptomRepository.findByIdAndPatient(id, patient).orElse(null);
	}
	
	public List<PatientSymptom> findByPatientAndDateBetween(String login, LocalDate dateStart, LocalDate dateEnd) {
		Patient patient = getPatient(login);
		return patientSymptomRepository.findByPatientAndDateBetween(patient, dateStart, dateEnd);
		
	}
	
	public List<PatientSymptom> findByPatientAndSymptomAndDateBetween(String login, String symptomName, LocalDate dateStart, LocalDate dateEnd) {
		Patient patient = getPatient(login);
		Symptom symptom = getSymptom(symptomName);
		return patientSymptomRepository.findByPatientAndSymptomAndDateBetween(patient, symptom, dateStart, dateEnd);
		
	}

}
