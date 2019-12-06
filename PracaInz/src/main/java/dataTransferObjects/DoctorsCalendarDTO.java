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
	private DoctorDTO doctor;
	private InstitutionDTO institution;
	private PatientDTO patient;
	
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
	public DoctorDTO getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}
	public InstitutionDTO getInstitution() {
		return institution;
	}
	public void setInstitution(InstitutionDTO institution) {
		this.institution = institution;
	}
	public PatientDTO getPatient() {
		return patient;
	}
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
	
	
}
