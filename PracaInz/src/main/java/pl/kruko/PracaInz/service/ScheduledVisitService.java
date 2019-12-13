package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.DoctorsCalendarDTO;
import dataTransferObjects.InstitutionDTO;
import dataTransferObjects.PatientDTO;
import dataTransferObjects.ScheduledVisitDTO;
import dataTransferObjects.VisitTypeDTO;
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
	private DoctorService doctorService;
	@Autowired
	private InstitutionService institutionService;

	@Autowired
	private ScheduledVisitRepository scheduledVisitRepository;
	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<ScheduledVisitDTO>>() {
	}.getType();

	private List<ScheduledVisitDTO> scheduledVisitDTO;

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
		scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		Collections.sort(scheduledVisitDTO);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatientAndDate(String login, LocalDateTime date) {
		Patient patient = getPatient(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByPatientAndDate(patient, date);
		scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatientAndVisitType(String login, String typeName) {
		Patient patient = getPatient(login);
		VisitTypeDTO visitTypeDTO = visitTypeService.findByName(typeName);
		VisitType visitType = modelMapper.map(visitTypeDTO, VisitType.class);
		List<ScheduledVisit> scheduledVisit = scheduledVisitRepository.findByPatientAndVisitType(patient, visitType);
		scheduledVisitDTO = modelMapper.map(scheduledVisit, listType);
		return scheduledVisitDTO;
	}

//	public List<ScheduledVisitDTO> findByPatientAndType(String login, int type) {
//		Patient patient = getPatient(login);
//		List<VisitType> visitType = visitTypeService.findByType(type);
//		List<ScheduledVisit> scheduledVisit = new ArrayList<>();
//		for (VisitType v : visitType) {
//			scheduledVisit.addAll(scheduledVisitRepository.findByPatientAndVisitType(patient, v));
//		}
//		scheduledVisitDTO = modelMapper.map(scheduledVisit, listType);
//		return scheduledVisitDTO;
//	}

	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

	public void addNewScheduledVisit(PatientDTO patient, VisitTypeDTO visitType, InstitutionDTO institution,
			DoctorDTO doctor, LocalDateTime dateTime) {
		ScheduledVisitDTO scheduledVisitDTO = new ScheduledVisitDTO(dateTime, visitType, doctor, institution, patient);
		ScheduledVisit scheduledVisit = modelMapper.map(scheduledVisitDTO, ScheduledVisit.class);
		scheduledVisitRepository.save(scheduledVisit);

	}

//	public void addNewEvent(String login, LocalDateTime dateTime, VisitTypeDTO visitType, DoctorDTO doctor, InstitutionDTO institution) {
//		Patient patient = getPatient(login);
//		PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
//		if (findByPatientAndDate(login, dateTime).isEmpty()) {
//			addNewScheduledVisit(patientDTO, visitType, institution, doctor, dateTime);
//		} else {
//			System.out.println("zaklepane");
//		}
//
//	}

	public void addNewEvent(String login, DoctorsCalendarDTO doctorCalendar) {
		Patient patient = getPatient(login);
		PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
		if (findByPatientAndDate(login, doctorCalendar.getDate()).isEmpty()) {
			addNewScheduledVisit(patientDTO, doctorCalendar.getVisitType(), doctorCalendar.getInstitution(),
					doctorCalendar.getDoctor(), doctorCalendar.getDate());
		} else {
			System.out.println("zaklepane");
		}

	}

	public List<LocalDate> getDates(List<ScheduledVisitDTO> scheduledVisitDTO) {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		for (ScheduledVisitDTO sV : scheduledVisitDTO) {
			if (!dates.contains(sV.getDate().toLocalDate())) {
				dates.add(sV.getDate().toLocalDate());
			}
		}
		return dates;
	}

}
