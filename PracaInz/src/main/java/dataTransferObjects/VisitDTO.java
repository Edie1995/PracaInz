package dataTransferObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VisitDTO implements Comparable<VisitDTO>{

	private Long id;
	private LocalDateTime date;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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

	public String getHour() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return date.format(formatter);
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		return "VisitDTO [id=" + id + ", date=" + date.format(formatter) + ", patient=" + patient + ", doctor=" + doctor + "]";
	}

	@Override
	public int compareTo(VisitDTO o) {
		return this.date.toLocalDate().compareTo(o.getDate().toLocalDate())*(-1);
	}

}
