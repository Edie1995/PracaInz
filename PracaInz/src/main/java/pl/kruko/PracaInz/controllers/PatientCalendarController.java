package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.ScheduledVisitDTO;
import pl.kruko.PracaInz.service.ScheduledVisitService;

@Controller
public class PatientCalendarController {

	ScheduledVisitService scheduledVisitService;

	@Autowired
	public PatientCalendarController(ScheduledVisitService scheduledVisitService) {
		super();
		this.scheduledVisitService = scheduledVisitService;
	}

	@GetMapping("/calendar.html")
	public String showAllEvents(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatient(login);
		List<LocalDate> dates = scheduledVisitService.getDates(scheduledVisitDTO);
		model.addAttribute("visits", scheduledVisitDTO);
		model.addAttribute("dates", dates);
		return "calendar.html";
	}


	@GetMapping("patientCalendar/{date}")
	public List<ScheduledVisitDTO> showEventsByDate(HttpServletRequest request, @PathVariable String date) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatientAndDate(login,
				LocalDateTime.parse(date));
		return scheduledVisitDTO;
	}

	@GetMapping("patientCalendar/type/{visitType}")
	public List<ScheduledVisitDTO> showEventsByVisitType(HttpServletRequest request, @PathVariable String typeName) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatientAndVisitType(login, typeName);
		return scheduledVisitDTO;
	}

//	@GetMapping("patientCalendar/all/{type}")
//	public List<ScheduledVisitDTO> showEventsByType(HttpServletRequest request, @PathVariable int type) {
//		String login = currentUserNameSimple(request);
//		List<ScheduledVisitDTO> scheduledVisitDTO = scheduledVisitService.findByPatientAndType(login, type);
//		return scheduledVisitDTO;
//	}

	@PostMapping("patientCalendar/addEvent")
	public void addEvent(HttpServletRequest request, @PathVariable String VisitTypeName, @PathVariable String date,
			@PathVariable String idDoctor, @PathVariable String idInstitution) {
		String login = currentUserNameSimple(request);
		scheduledVisitService.addNewEvent(login, VisitTypeName, idDoctor, idInstitution, date);
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return login;
	}
	
//	@GetMapping("/searchDoctor.html")
//	public String searchDoctor() {
//		return "/searchDoctor.html";
//	}

}
