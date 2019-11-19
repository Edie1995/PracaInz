package pl.kruko.PracaInz.service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import dataTransferObjects.DoctorsCalendarDTO;
import dataTransferObjects.VisitDTO;
import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.DoctorsCalendar;
import pl.kruko.PracaInz.repo.DoctorsCalendarRepository;

public class DoctorsCalendarService {

	private DoctorsCalendarRepository doctorsCalendarRepository;

	@Autowired
	public DoctorsCalendarService(DoctorsCalendarRepository doctorsCalendarRepository) {
		super();
		this.doctorsCalendarRepository = doctorsCalendarRepository;
	}

	private ModelMapper modelMapper = new ModelMapper();
	private Type listType = new TypeToken<List<DoctorsCalendarDTO>>() {
	}.getType();
	
	public List<DoctorsCalendarDTO> findByDoctorAndDate(Doctor doctor, String date){
		LocalDateTime dateTime = LocalDateTime.parse(date);
		List<DoctorsCalendar> events = doctorsCalendarRepository.findByDoctorAndDateTime(doctor, dateTime);
		List<DoctorsCalendarDTO> eventsDTO = modelMapper.map(events, listType);
		return eventsDTO;		
	}
	
	public List<DoctorsCalendarDTO> findByDoctorAndDateBetween(Doctor doctor, String startDate, String endDate){
		LocalDateTime startDateTime = LocalDateTime.parse(startDate);
		LocalDateTime endDateTime = LocalDateTime.parse(endDate);
		List<DoctorsCalendar> events = doctorsCalendarRepository.findByDoctorAndDateTimeBetween(doctor, startDateTime, endDateTime);
		List<DoctorsCalendarDTO> eventsDTO = modelMapper.map(events, listType);
		return eventsDTO;		
	}
	
}
