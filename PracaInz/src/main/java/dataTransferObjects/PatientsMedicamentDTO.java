package dataTransferObjects;

import pl.kruko.PracaInz.models.Status;

public class PatientsMedicamentDTO implements Comparable<PatientsMedicamentDTO> {

	private Long id;
	private int dosage;
	private int frequency;
	private VisitDTO visit;
	private MedicamentDTO medicament;
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

	public VisitDTO getVisit() {
		return visit;
	}

	public void setVisit(VisitDTO visit) {
		this.visit = visit;
	}

	public MedicamentDTO getMedicament() {
		return medicament;
	}

	public void setMedicament(MedicamentDTO medicament) {
		this.medicament = medicament;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int compareTo(PatientsMedicamentDTO o) {
		return this.visit.compareTo(o.getVisit());
	}

}
