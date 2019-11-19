package pl.kruko.PracaInz.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity (name = "DoctorCalendar")
@Table (name = "doctors_calendars")
public class DoctorsCalendar {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateTime;
	//private LocalTime hour;
	@ManyToOne
	private Doctor doctor;
	@ManyToOne
	private Institution institution;
	@ManyToOne(optional = true)
	private Patient patient;
	
	public DoctorsCalendar() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return dateTime;
	}

	public void setDate(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
//	public LocalTime getHour() {
//		return hour;
//	}
//
//	public void setHour(LocalTime hour) {
//		this.hour = hour;
//	}

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

//	@Override
//	public String toString() {
//		return "DoctorsCalendar [id=" + id + ", date=" + date + ", hour=" + hour + ", doctor=" + doctor
//				+ ", institution=" + institution + ", patient=" + patient + "]";
//	}
	
	
}
