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
	private VisitDTO visit;
	private VisitTypeDTO visitType;
	private DoctorDTO doctor;
	private InstitutionDTO institution;
	private PatientDTO patient;
	
	public ScheduledVisitDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public ScheduledVisitDTO(LocalDateTime date, VisitTypeDTO visitType, DoctorDTO doctor, InstitutionDTO institution,
			PatientDTO patient) {
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

	public VisitDTO getVisit() {
		return visit;
	}

	public void setVisit(VisitDTO visit) {
		this.visit = visit;
	}

	public VisitTypeDTO getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitTypeDTO visitType) {
		this.visitType = visitType;
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
