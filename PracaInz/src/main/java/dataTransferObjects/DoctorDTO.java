package dataTransferObjects;

import java.util.List;

import pl.kruko.PracaInz.models.Status;

public class DoctorDTO extends DoctorForSearchDTO {
	private Status status;
	private SpecializationDTO specializtaion;
	private List<InstitutionDTO> institutions;
	private UserDTO user;

	public DoctorDTO() {
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
		return getId() + " " + getLastName() + ",<br> numer: " + getTelephoneNumber() + ",<br> specjalizacja: " + specializtaion;
	}

}
