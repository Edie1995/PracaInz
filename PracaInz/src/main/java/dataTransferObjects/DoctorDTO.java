package dataTransferObjects;

import java.util.List;

import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Specialization;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.models.User;

public class DoctorDTO {
	private Long id;
	private int telephoneNumber;
	private Status status;
	private SpecializationDTO specializtaion;
	private List<InstitutionDTO> institutions;
	private User user;
	
	public DoctorDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(int telephoneNumber) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
