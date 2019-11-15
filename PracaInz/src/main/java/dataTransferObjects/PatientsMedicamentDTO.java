package dataTransferObjects;

import pl.kruko.PracaInz.models.Medicament;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.models.Visit;

public class PatientsMedicamentDTO {

	
	private Long id;
	private int dosage;
	private int frequency;
	private Visit visit;
	private Medicament medicament;
	private Status status;
	
	public PatientsMedicamentDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
