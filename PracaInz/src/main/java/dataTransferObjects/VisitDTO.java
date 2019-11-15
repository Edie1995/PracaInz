package dataTransferObjects;

import java.time.LocalDate;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Patient;

public class VisitDTO {

	private Long id;
	private LocalDate date;
	private Patient patient;
	private Doctor doctor;
	
	public VisitDTO() {
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	
}
