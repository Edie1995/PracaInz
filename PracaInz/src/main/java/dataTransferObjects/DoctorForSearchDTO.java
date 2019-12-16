package dataTransferObjects;

import pl.kruko.PracaInz.models.Person;

public class DoctorForSearchDTO extends Person{
	private Long id;
	private Long telephoneNumber;
	
	
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
	
}
