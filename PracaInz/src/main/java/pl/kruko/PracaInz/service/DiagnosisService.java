package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DiagnosisDTO;
import pl.kruko.PracaInz.models.Diagnosis;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.DiagnosisRepository;

@Service
public class DiagnosisService {

	private DiagnosisRepository diagnosisRepository;
	private VisitService visitService;
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<DiagnosisDTO>>() {
	}.getType();

	@Autowired
	public DiagnosisService(DiagnosisRepository diagnosisRepository, VisitService visitService) {
		super();
		this.diagnosisRepository = diagnosisRepository;
		this.visitService = visitService;
	}

	public List<DiagnosisDTO> findByVisit(String login) {
		List<Visit> visitsDTO = getVisit(login);
		Type listTypeVisit = new TypeToken<List<Visit>>() {
		}.getType();
		List<Visit> visits = modelMapper.map(visitsDTO, listTypeVisit);
		List<Diagnosis> diagnosis = new ArrayList<>();
		for (Visit v : visits) {
			diagnosis.addAll(diagnosisRepository.findByVisit(v));
		}
		List<DiagnosisDTO> diagnosisDTO = modelMapper.map(diagnosis, listType);
		Collections.sort(diagnosisDTO);
		return diagnosisDTO;

	}

	public List<DiagnosisDTO> findByVisitAndName(String login, String name) {
		List<Visit> visitsDTO = getVisit(login);
		Type listTypeVisit = new TypeToken<List<Visit>>() {
		}.getType();
		List<Visit> visits = modelMapper.map(visitsDTO, listTypeVisit);
		List<Diagnosis> diagnosis = new ArrayList<>();
		for (Visit v : visits) {
			diagnosis.addAll(diagnosisRepository.findByVisitAndName(v, name));
		}
		List<DiagnosisDTO> diagnosisDTO = modelMapper.map(diagnosis, listType);
		return diagnosisDTO;
	}
	
	public List<DiagnosisDTO> findByStatusAndPatient(Integer statusNumber, String login) {
		Status status = getStatus(statusNumber);
		List<Visit> visits = getVisit(login);
		List<Diagnosis> diagnosis = new ArrayList<>();
		for (Visit v : visits) {
			diagnosis.addAll(diagnosisRepository.findByVisitAndStatus(v, status));
		}
		List<DiagnosisDTO> diagnosisDTO = modelMapper.map(diagnosis, listType);
		Collections.sort(diagnosisDTO);
		return diagnosisDTO;
	}
	
	
	public List<Visit> getVisit(String login) {
		List<Visit> visits = visitService.findByPatient(login);
		return visits;
	}

	public Status getStatus(Integer statusNumber) {
		Status status;
		if (statusNumber == 0) {
			status = Status.ACTIVE;
		} else {
			status = Status.INACTIVE;
		}
		return status;
	}
//	public List<DiagnosisDTO> findByVisitAndDate(String login, String startDateString, String endDateString){
//		LocalDate startDate = LocalDate.parse(startDateString);
//		LocalDate endDate = LocalDate.parse(endDateString);
//		List<VisitDTO> visitsDTO = visitService.findByPatientAndDateBetween(login, startDate, endDate);
//		Type listTypeVisit = new TypeToken<List<Visit>>() {
//		}.getType();
//		List<Visit> visits = modelMapper.map(visitsDTO, listTypeVisit);
//		List<Diagnosis> diagnosis = new ArrayList<>();
//		for (Visit v : visits) {
//			diagnosis.addAll(diagnosisRepository.findByVisit(v));
//		}
//		List<DiagnosisDTO> diagnosisDTO = modelMapper.map(diagnosis, listType);
//		return diagnosisDTO;
//	}

}
