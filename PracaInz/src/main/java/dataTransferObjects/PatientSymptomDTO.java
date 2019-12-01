package dataTransferObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Symptom;
import pl.kruko.PracaInz.models.Visit;

public class PatientSymptomDTO implements Comparable <PatientSymptomDTO>{

	private Long id;
	private LocalDate date;
	private Patient patient;
	private Symptom symptom;
	private Visit visit;
	
	public PatientSymptomDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getSymptomName() {
		return symptom.getName();
	}
	
	
	public String getVisitName() {
		String name;
		try {
			name = visit.getDate().toString();
		}catch(NullPointerException e) {
			 name="-";
		}
		return name;
			
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
	
	@Override
	public int compareTo(PatientSymptomDTO pS) {
		return this.date.compareTo(pS.getDate())*(-1);
	}
	@Override
	public String toString() {
		return "PatientSymptomDTO [date=" + date + ", symptom=" + symptom.getName() + "]";
	}

	
	
    
    
}
