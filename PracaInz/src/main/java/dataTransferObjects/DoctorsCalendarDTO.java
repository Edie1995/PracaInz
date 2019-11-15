package dataTransferObjects;

import java.time.LocalDate;
import java.time.LocalTime;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Patient;

public class DoctorsCalendarDTO {

	
	private Long id;
	private LocalDate date;
	private LocalTime hour;
	private Doctor doctor;
	private Institution institution;
	private Patient patient;
	
	public DoctorsCalendarDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getHour() {
		return hour;
	}
	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
}
