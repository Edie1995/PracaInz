package dataTransferObjects;

import java.util.List;

import pl.kruko.PracaInz.models.Status;

public class InstitutionDTO {

	private Long id;
	private String name;
	private String city;
	private String adress;
	private Long phoneNumber;
	private Status status;
	private List<DoctorDTO> doctors;

	public InstitutionDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<DoctorDTO> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return name + ",<br> miasto: " + city + ",<br> ulica: " + adress;
	}

	
}
