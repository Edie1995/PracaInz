package pl.kruko.PracaInz.models;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity (name = "Visit")
@Table (name = "visits")
public class Visit {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	@ManyToOne
	private Patient patient;
	@ManyToOne
	private Doctor doctor;
	
	public Visit() {
		
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

//	@Override
//	public String toString() {
//		return "Visit [id=" + id + ", date=" + date + ", patient=" + patient + ", doctor=" + doctor + "]";
//	}
//	
	
}
