package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dataTransferObjects.ScheduledVisitToCalendarDTO;
import pl.kruko.PracaInz.service.ScheduledVisitService;

@Controller
public class PatientCalendarController {

	private ScheduledVisitService scheduledVisitService;

	@Autowired
	public PatientCalendarController(ScheduledVisitService scheduledVisitService) {
		super();
		this.scheduledVisitService = scheduledVisitService;
	}

	@GetMapping("/calendar.html")
	public String showAllEvents(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		List<ScheduledVisitToCalendarDTO> scheduledVisitDTO = scheduledVisitService.findByPatient(login);
		List<LocalDate> dates = scheduledVisitService.getDates(scheduledVisitDTO);
		model.addAttribute("visits", scheduledVisitDTO);
		model.addAttribute("dates", dates);
		return "calendar.html";
	}


	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return login;
	}

}
