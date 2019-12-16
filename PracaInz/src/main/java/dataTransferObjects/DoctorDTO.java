package dataTransferObjects;

import java.util.List;

import pl.kruko.PracaInz.models.Person;
import pl.kruko.PracaInz.models.Status;

public class DoctorDTO extends Person{
	private Long id;
	private Long telephoneNumber;
	private Status status;
	private SpecializationDTO specializtaion;
	private List<InstitutionDTO> institutions;
	private UserDTO user;
	
	public DoctorDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public SpecializationDTO getSpecializtaion() {
		return specializtaion;
	}

	public void setSpecializtaion(SpecializationDTO specializtaion) {
		this.specializtaion = specializtaion;
	}

	public List<InstitutionDTO> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<InstitutionDTO> institutions) {
		this.institutions = institutions;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return id + " " + getLastName() + ",<br> numer: " + telephoneNumber + ",<br> specjalizacja: " + specializtaion;
	}
	
	
	
	
}
