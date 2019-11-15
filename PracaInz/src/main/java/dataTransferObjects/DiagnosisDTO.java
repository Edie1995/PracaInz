package dataTransferObjects;

import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.models.Visit;

public class DiagnosisDTO {

	private Long id;
	private String name;
	private String details;
	private Visit visit;
	Status status;
	
	public DiagnosisDTO() {
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
