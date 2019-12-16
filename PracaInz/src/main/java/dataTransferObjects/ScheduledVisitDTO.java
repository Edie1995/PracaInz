package dataTransferObjects;

import java.time.LocalDateTime;

public class ScheduledVisitDTO extends ScheduledVisitToCalendarDTO {

	public ScheduledVisitDTO() {
		super();
	}

	public ScheduledVisitDTO(LocalDateTime date, VisitTypeDTO visitType, DoctorDTO doctor, InstitutionDTO institution,
			PatientDTO patient) {
		super(date, visitType, doctor, institution, patient);

	}

	private VisitDTO visit;

	public VisitDTO getVisit() {
		return visit;
	}

	public void setVisit(VisitDTO visit) {
		this.visit = visit;
	}

}
