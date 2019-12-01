package dataTransferObjects;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Role;

public class UserDTO {
	
	private Long id;
	private String login;
	private Doctor doctor;
	private Patient patient;
	private Role role;
	private String password;
	
	public UserDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
