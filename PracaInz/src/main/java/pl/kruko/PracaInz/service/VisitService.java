package pl.kruko.PracaInz.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorForSearchDTO;
import dataTransferObjects.PatientDTO;
import dataTransferObjects.ScheduledVisitDTO;
import dataTransferObjects.VisitDTO;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.repo.VisitRepository;

@Service
public class VisitService {

	private VisitRepository visitRepository;
	private PatientService patientService;
	private DoctorService doctorService;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	public VisitService(VisitRepository visitRepository, PatientService patientService, DoctorService doctorService) {
		super();
		this.visitRepository = visitRepository;
		this.patientService = patientService;
		this.doctorService = doctorService;
	}

	public List<Visit> findByPatient(String login) {
		Patient patient = getPatient(login);
		List<Visit> visits = visitRepository.findByPatient(patient);
		return visits;
	}

	public Long save(VisitDTO visitDTO) {
		Visit visit = visitRepository.save(modelMapper.map(visitDTO, Visit.class));
		return visit.getId();
	}

	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

	public List<Visit> findByPatientDTO(PatientDTO patientDTO) {
		Patient patient = modelMapper.map(patientDTO, Patient.class);
		List<Visit> visits = visitRepository.findByPatient(patient);
		return visits;
	}

	public VisitDTO createVisitDTO(String login, ScheduledVisitDTO scheduledVisit) {
		DoctorForSearchDTO doctorDTO = doctorService.findDTObyUser(login);
		VisitDTO visitDTO = new VisitDTO();
		visitDTO.setDoctor(doctorDTO);
		visitDTO.setPatient(scheduledVisit.getPatient());
		visitDTO.setDate(scheduledVisit.getDate());
		return visitDTO;
	}
}
