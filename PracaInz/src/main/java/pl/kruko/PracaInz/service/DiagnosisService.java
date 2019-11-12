package pl.kruko.PracaInz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Diagnosis;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.DiagnosisRepository;

@Service
public class DiagnosisService {

	private DiagnosisRepository diagnosisRepository;
	private VisitService visitService;
	
	@Autowired
	public DiagnosisService(DiagnosisRepository diagnosisRepository, VisitService visitService) {
		super();
		this.diagnosisRepository = diagnosisRepository;
		this.visitService = visitService;
	}
	
	public List<Diagnosis> findByVisit(String login){
		Visit visit = visitService.findByPatient(login);
		System.out.println(visit);
		List<Diagnosis> diagnosis = diagnosisRepository.findByVisit(visit);
		System.out.println(diagnosis);
		return diagnosis;
		
	}
	
	public List<Diagnosis> findByVisitAndName(String login, String name){
		Visit visit = visitService.findByPatient(login);
		List<Diagnosis> diagnosis =diagnosisRepository.findByVisitAndName(visit, name);
		return diagnosis;
	}
	
	
}
