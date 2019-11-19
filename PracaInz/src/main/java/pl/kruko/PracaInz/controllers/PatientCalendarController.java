package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import dataTransferObjects.ScheduledVisitDTO;
import pl.kruko.PracaInz.service.ScheduledVisitService;

@RestController
public class PatientCalendarController {

	ScheduledVisitService scheduledVisitService;

	@Autowired
	public PatientCalendarController(ScheduledVisitService scheduledVisitService) {
		super();
		this.scheduledVisitService = scheduledVisitService;
	}

	@GetMapping("patientCalendar")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<ScheduledVisitDTO> showAllEvents(HttpServletRequest request) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatient(login);
		System.out.println(scheduledVisitDTO);
		return scheduledVisitDTO;
	}

	@GetMapping("patientCalendar/{date}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<ScheduledVisitDTO> showEventsByDate(HttpServletRequest request, @PathVariable String date) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatientAndDate(login, LocalDate.parse(date));
		System.out.println(scheduledVisitDTO);
		return scheduledVisitDTO;
	}

	@GetMapping("patientCalendar/type/{visitType}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<ScheduledVisitDTO> showEventsByVisitType(HttpServletRequest request, @PathVariable String typeName) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatientAndVisitType(login, typeName);
		return scheduledVisitDTO;
	}

	@GetMapping("patientCalendar/all/{type}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public List<ScheduledVisitDTO> showEventsByType(HttpServletRequest request, @PathVariable int type){
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatientAndType(login, type);
		return scheduledVisitDTO;
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}