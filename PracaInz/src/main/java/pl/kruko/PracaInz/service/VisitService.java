package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DiagnosisDTO;
import dataTransferObjects.VisitDTO;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.VisitRepository;

@Service
public class VisitService {

	private PatientService patientService;
	private VisitRepository visitRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<VisitDTO>>() {
	}.getType();

	@Autowired
	public VisitService(PatientService patientService, VisitRepository visitRepository) {
		super();
		this.patientService = patientService;
		this.visitRepository = visitRepository;
	}

	public List<VisitDTO> findByPatient(String login) {
		Patient patient = getPatient(login);
		List<Visit> visits = visitRepository.findByPatient(patient);
		List<VisitDTO> visitsDTO = modelMapper.map(visits, listType);
		return visitsDTO;
	}

	
	public List<VisitDTO> findByPatientAndDate(String login, LocalDate date){
		Patient patient = getPatient(login);
		List<Visit> visits = visitRepository.findByPatientAndDate(patient, date);
		List<VisitDTO> visitsDTO = modelMapper.map(visits, listType);
		return visitsDTO;
	}
	
	public List<VisitDTO> findByPatientAndDateBetween(String login, LocalDate startDate, LocalDate endDate){
		Patient patient = getPatient(login);
		List<Visit> visits = visitRepository.findByPatientAndDateBetween(patient, startDate, endDate);
		List<VisitDTO> visitsDTO = modelMapper.map(visits, listType);
		return visitsDTO;
	}
	
	
	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

}
