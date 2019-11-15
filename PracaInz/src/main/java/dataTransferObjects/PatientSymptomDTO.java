package dataTransferObjects;

import java.time.LocalDate;

import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Symptom;
import pl.kruko.PracaInz.models.Visit;

public class PatientSymptomDTO {

	private Long id;
	private LocalDate date;
	private Patient patient;
	private Symptom symptom;
	private Visit visit;
	
	public PatientSymptomDTO() {
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

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	
	
}
