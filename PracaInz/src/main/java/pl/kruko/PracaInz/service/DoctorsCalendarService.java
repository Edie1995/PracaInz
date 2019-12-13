package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dataTransferObjects.DoctorsCalendarDTO;
import dataTransferObjects.PatientDTO;
import dataTransferObjects.ScheduledVisitDTO;
import dataTransferObjects.VisitTypeDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.DoctorsCalendar;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.models.VisitType;
import pl.kruko.PracaInz.repo.DoctorsCalendarRepository;

@Service
public class DoctorsCalendarService {

	private DoctorsCalendarRepository doctorsCalendarRepository;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private InstitutionService institutionService;
	@Autowired
	private PatientService patientService;

	@Autowired
	public DoctorsCalendarService(DoctorsCalendarRepository doctorsCalendarRepository) {
		super();
		this.doctorsCalendarRepository = doctorsCalendarRepository;
	}

	private ModelMapper modelMapper = new ModelMapper();
	private Type listType=new TypeToken<List<DoctorsCalendarDTO>>(){}.getType();

	public List<DoctorsCalendarDTO> findByDoctorAndDate(Doctor doctor, String date) {
		LocalDateTime dateTime = LocalDateTime.parse(date);
		List<DoctorsCalendar> events = doctorsCalendarRepository.findByDoctorAndDateTime(doctor, dateTime);
		List<DoctorsCalendarDTO> eventsDTO = modelMapper.map(events, listType);
		return eventsDTO;
	}

	public List<DoctorsCalendarDTO> findByDoctorAndDateBetween(Doctor doctor, String startDate, String endDate) {
		LocalDateTime startDateTime = LocalDateTime.parse(startDate);
		LocalDateTime endDateTime = LocalDateTime.parse(endDate);
		List<DoctorsCalendar> events = doctorsCalendarRepository.findByDoctorAndDateTimeBetween(doctor, startDateTime,
				endDateTime);
		List<DoctorsCalendarDTO> eventsDTO = modelMapper.map(events, listType);
		return eventsDTO;
	}

	public List<DoctorsCalendarDTO> findByDoctorAndDateAndTypeAndCity(String doctorName, LocalDate date,
			VisitTypeDTO visitTypeDTO, String city) {
		VisitType visitType = modelMapper.map(visitTypeDTO, VisitType.class);
		LocalDate date2 = date.plusDays(7);
		List<Institution> institutions = institutionService.findByCityAndStatus(city, Status.ACTIVE);
		List<DoctorsCalendar> calendars = new ArrayList<DoctorsCalendar>();
		List<Doctor> doctors = doctorService.findByNameAndSpecialization(doctorName, visitType.getName(), city);

		if (institutions.isEmpty() && doctors.isEmpty()) {
			calendars = doctorsCalendarRepository.findByDoctorAndDateBetweenAndTypeAndInstitutionAndPatient(null, date,
					date2, visitType, null);
		} else if (institutions.isEmpty()) {
			for (Doctor d : doctors) {
				calendars = doctorsCalendarRepository.findByDoctorAndDateBetweenAndTypeAndInstitutionAndPatient(d, date,
						date2, visitType, null);
			}
		} else if (doctors.isEmpty()) {
			for (Institution i : institutions) {
				calendars = doctorsCalendarRepository.findByDoctorAndDateBetweenAndTypeAndInstitutionAndPatient(null,
						date, date2, visitType, i);
			}
		} else {
			for (Doctor d : doctors) {
				for (Institution i : institutions) {
					calendars = doctorsCalendarRepository.findByDoctorAndDateBetweenAndTypeAndInstitutionAndPatient(d,
							date, date2, visitType, i);
				}
			}
		}
		return modelMapper.map(calendars, listType);

	}
	
	public Patient getPatient(String login) {
		Patient patient = patientService.findByUser(login);
		return patient;
	}

	public void addPatientToDoctorCalendar(String login, DoctorsCalendarDTO doctorCalendar) {
		Patient patient = patientService.findByUser(login);
		DoctorsCalendar doctorsCalendar = modelMapper.map(doctorCalendar, DoctorsCalendar.class);		
		doctorsCalendar.setPatient(patient);
		doctorsCalendarRepository.save(doctorsCalendar);
	}

	public List<LocalDate> getDates(List<DoctorsCalendarDTO> doctorsCalendarDTO) {
		List<LocalDate> dates = new ArrayList<LocalDate>();
		for (DoctorsCalendarDTO dC : doctorsCalendarDTO) {
			if (!dates.contains(dC.getDate().toLocalDate())) {
				dates.add(dC.getDate().toLocalDate());
			}
		}
		return dates;
	}

//	public List<LocalTime> getTime(List<DoctorsCalendarDTO> doctorsCalendarDTO) {
//		List<LocalTime> times = new ArrayList<LocalTime>();
//		for (DoctorsCalendarDTO dC : doctorsCalendarDTO) {
//			if (!times.contains(dC.getDate().toLocalTime())) {
//				times.add(dC.getDate().toLocalTime());
//			}
//		}
//		return times;
//	}
}
