package dataTransferObjects;

import java.time.LocalDate;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Patient;

public class VisitDTO {

	private Long id;
	private LocalDate date;
	private PatientDTO patient;
	private DoctorDTO doctor;
	
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

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "VisitDTO [id=" + id + ", date=" + date + ", patient=" + patient + ", doctor=" + doctor + "]";
	}
	
	
	
	
}
