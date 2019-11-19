package dataTransferObjects;

import java.time.LocalDateTime;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Visit;
import pl.kruko.PracaInz.models.VisitType;

public class ScheduledVisitDTO {

	private Long id;
	private LocalDateTime date;
	private Visit visit;
	private VisitType visitType;
	private Doctor doctor;
	private Institution institution;
	private Patient patient;
	
	public ScheduledVisitDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public ScheduledVisitDTO(LocalDateTime date, VisitType visitType, Doctor doctor, Institution institution,
			Patient patient) {
		super();
		this.date = date;
		this.visitType = visitType;
		this.doctor = doctor;
		this.institution = institution;
		this.patient = patient;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
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
