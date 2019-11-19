package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.InstitutionDTO;
import dataTransferObjects.ScheduledVisitDTO;
import dataTransferObjects.VisitTypeDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.ScheduledVisit;
import pl.kruko.PracaInz.models.VisitType;
import pl.kruko.PracaInz.repo.ScheduledVisitRepository;

@Service
public class ScheduledVisitService {
	@Autowired
	private PatientService patientService;
	@Autowired
	private VisitTypeService visitTypeService;

	@Autowired
	private ScheduledVisitRepository scheduledVisitRepository;
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<ScheduledVisitDTO>>() {
	}.getType();

	@Autowired
	public ScheduledVisitService(ScheduledVisitRepository scheduledVisitRepository) {
		super();
		this.scheduledVisitRepository = scheduledVisitRepository;
	}

	public List<ScheduledVisitDTO> findByPatientToDTO(String login) {
		Patient patient = getPatient(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByPatient(patient);
		List<ScheduledVisitDTO> scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatient(String login) {
		Patient patient = getPatient(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByPatient(patient);
		List<ScheduledVisitDTO> scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatientAndDate(String login, LocalDate date) {
		Patient patient = getPatient(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByPatientAndDate(patient, date);
		System.out.println(scheduledVisits);
		List<ScheduledVisitDTO> scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatientAndVisitType(String login, String typeName) {
		Patient patient = getPatient(login);
		VisitType visitType = visitTypeService.findByName(typeName);
		List<ScheduledVisit> scheduledVisit = scheduledVisitRepository.findByPatientAndVisitType(patient, visitType);
		List<ScheduledVisitDTO> scheduledVisitDTO = modelMapper.map(scheduledVisit, listType);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatientAndType(String login, int type) {
		Patient patient = getPatient(login);
		List<VisitType> visitType = visitTypeService.findByType(type);
		List<ScheduledVisit> scheduledVisit = new ArrayList<>();
		for (VisitType v : visitType) {
			scheduledVisit.addAll(scheduledVisitRepository.findByPatientAndVisitType(patient, v));
		}
		List<ScheduledVisitDTO> scheduledVisitDTO = modelMapper.map(scheduledVisit, listType);
		return scheduledVisitDTO;
	}

	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

	public void addNewScheduledVisit(Patient patient, VisitType visitType, Institution institution, Doctor doctor,
			LocalDateTime dateTime) {
		ScheduledVisitDTO scheduledVisitDTO = new ScheduledVisitDTO(dateTime, visitType, doctor, institution, patient);
		ScheduledVisit scheduledVisit = modelMapper.map(scheduledVisitDTO, ScheduledVisit.class);
		scheduledVisitRepository.save(scheduledVisit);

	}

	public void addNewEvent(String login, String VisitTypeName, InstitutionDTO institutionDTO, DoctorDTO doctorDTO,
			String date) {
		LocalDateTime dateTime = LocalDateTime.parse(date);
		Patient patient = getPatient(login);
		Institution institution = modelMapper.map(institutionDTO, Institution.class);
		VisitType visitType = visitTypeService.findByName(VisitTypeName);
		Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
		addNewScheduledVisit(patient, visitType, institution, doctor, dateTime);

	}

}
