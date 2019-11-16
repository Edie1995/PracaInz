package pl.kruko.PracaInz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.VisitRepository;

@Service
public class VisitService {

	private PatientService patientService;
	private VisitRepository visitRepository;
	
	
	@Autowired
	public VisitService(PatientService patientService, VisitRepository visitRepository) {
		super();
		this.patientService = patientService;
		this.visitRepository = visitRepository;
	}

	public List<Visit> findByPatient(String login) {
		Patient patient =  patientService.findByUser(login);
		System.out.println(patient);
		return visitRepository.findByPatient(patient);
	}
}
