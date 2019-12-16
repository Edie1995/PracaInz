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
import dataTransferObjects.ScheduledVisitToCalendarDTO;
import dataTransferObjects.VisitTypeDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.ScheduledVisit;
import pl.kruko.PracaInz.repo.ScheduledVisitRepository;

@Service
public class ScheduledVisitService {

	private PatientService patientService;
	private DoctorService doctorService;
	private DoctorsCalendarService doctorsCalendarService;
	private ScheduledVisitRepository scheduledVisitRepository;

	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<ScheduledVisitDTO>>() {
	}.getType();

	@Autowired
	public ScheduledVisitService(PatientService patientService,
			DoctorService doctorService, DoctorsCalendarService doctorsCalendarService,
			ScheduledVisitRepository scheduledVisitRepository) {
		super();
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.doctorsCalendarService = doctorsCalendarService;
		this.scheduledVisitRepository = scheduledVisitRepository;
	}

	public List<ScheduledVisitToCalendarDTO> findByPatient(String login) {
		Patient patient = getPatient(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByPatient(patient);
		Type listType = new TypeToken<List<ScheduledVisitToCalendarDTO>>() {
		}.getType();
		List<ScheduledVisitToCalendarDTO> scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		Collections.sort(scheduledVisitDTO);
		return scheduledVisitDTO;
	}

	public List<ScheduledVisitDTO> findByPatientAndDate(String login, LocalDateTime date) {
		Patient patient = getPatient(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByPatientAndDate(patient, date);
		List<ScheduledVisitDTO> scheduledVisitDTO = modelMapper.map(scheduledVisits, listType);
		return scheduledVisitDTO;
	}

	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

	public void addNewScheduledVisit(PatientDTO patient, VisitTypeDTO visitType, InstitutionDTO institution,
			DoctorDTO doctor, LocalDateTime dateTime) {
		ScheduledVisitToCalendarDTO scheduledVisitDTO = new ScheduledVisitToCalendarDTO(dateTime, visitType, doctor, institution, patient);
		ScheduledVisit scheduledVisit = modelMapper.map(scheduledVisitDTO, ScheduledVisit.class);
		scheduledVisitRepository.save(scheduledVisit);

	}

	public void addNewEvent(String login, DoctorsCalendarDTO doctorCalendar) {
		Patient patient = getPatient(login);
		PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
		if (findByPatientAndDate(login, doctorCalendar.getDate()).isEmpty()) {
			addNewScheduledVisit(patientDTO, doctorCalendar.getVisitType(), doctorCalendar.getInstitution(),
					doctorCalendar.getDoctor(), doctorCalendar.getDate());
		} else {
			System.out.println("zaklepane");
		}
		doctorsCalendarService.addPatientToDoctorCalendar(login, doctorCalendar);

	}

	public List<LocalDate> getDates(List<ScheduledVisitToCalendarDTO> scheduledVisitDTO) {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		for (ScheduledVisitToCalendarDTO sV : scheduledVisitDTO) {
			if (!dates.contains(sV.getDate().toLocalDate())) {
				dates.add(sV.getDate().toLocalDate());
			}
		}
		return dates;
	}

	public List<ScheduledVisitDTO> findByDoctorAndDate(String login, LocalDate dateString) {
		LocalDateTime dateStart = dateString.atStartOfDay();
		LocalDateTime dateEnd = dateString.plusDays(1).atStartOfDay();
		Doctor doctor = doctorService.findByUser(login);
		List<ScheduledVisit> scheduledVisits = scheduledVisitRepository.findByDoctorAndDateBetween(doctor, dateStart,
				dateEnd);
		List<ScheduledVisitDTO> scheduledVisitsDTO = modelMapper.map(scheduledVisits, listType);
		Collections.sort(scheduledVisitsDTO);
		return scheduledVisitsDTO;
	}

}
