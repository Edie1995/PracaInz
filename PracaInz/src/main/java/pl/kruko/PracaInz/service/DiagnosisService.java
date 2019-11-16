package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DiagnosisDTO;
import pl.kruko.PracaInz.models.Diagnosis;
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
	
	public List<DiagnosisDTO> findByVisit(String login){
		List<Visit> visits = visitService.findByPatient(login);
		System.out.println(visits);
		List<Diagnosis> diagnosis = new ArrayList<>();
		for(Visit v: visits) {
		diagnosis.addAll(diagnosisRepository.findByVisit(v));
		}
		List<DiagnosisDTO> diagnosisDTO = modelMapper.map(diagnosis, listType);
		System.out.println(diagnosis);
		return diagnosisDTO;
		
	}
	
	public List<DiagnosisDTO> findByVisitAndName(String login, String name){
		List<Visit> visits = visitService.findByPatient(login);
		List<Diagnosis> diagnosis = new ArrayList<>();
		for(Visit v: visits) {
			diagnosis.addAll(diagnosisRepository.findByVisitAndName(v, name));
		}
		List<DiagnosisDTO> diagnosisDTO = modelMapper.map(diagnosis, listType);
		System.out.println(diagnosis);
		return diagnosisDTO;
	}
	
	
}
