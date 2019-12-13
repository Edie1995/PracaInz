package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.DoctorsCalendarDTO;
import dataTransferObjects.VisitTypeDTO;
import pl.kruko.PracaInz.models.Type;
import pl.kruko.PracaInz.service.DoctorService;
import pl.kruko.PracaInz.service.DoctorsCalendarService;
import pl.kruko.PracaInz.service.ScheduledVisitService;
import pl.kruko.PracaInz.service.VisitTypeService;

@Controller
public class AddVisitController {

	@Autowired
	ScheduledVisitService scheduledVisitService;

	@Autowired
	DoctorService doctorService;

	@Autowired
	DoctorsCalendarService doctorsCalendarService;

	@Autowired
	VisitTypeService visitTypeService;
	List<VisitTypeDTO> visitsTypesDTO;
	List<DoctorsCalendarDTO> doctorsCalendarDTO;

	@GetMapping("/searchDoctor.html")
	public String searchDoctor(HttpServletRequest request, Model model) {
		model.addAttribute("visitTypes", visitsTypesDTO);
		model.addAttribute("visitType", new VisitTypeDTO());
		List<DoctorDTO> doctorsDTO = doctorService.findAll();
		model.addAttribute("doctors", doctorsDTO);
		model.addAttribute("doctor", new DoctorDTO());

		return "searchDoctor.html";
	}

	@GetMapping("/searchDoctor/exam")
	public String searchExam(HttpServletRequest request, Model model) {
		visitsTypesDTO = visitTypeService.findByType(Type.EXAMINATION);
		return "redirect:/searchDoctor.html";
	}

	@GetMapping("/searchDoctor/visit")
	public String searchDoctorVisit(HttpServletRequest request, Model model) {
		visitsTypesDTO = visitTypeService.findByType(Type.MEDICAL_VISIT);
		return "redirect:/searchDoctor.html";
	}

	@PostMapping("/addVisit/search")
	public String visitProposition(HttpServletRequest request,
			@ModelAttribute(value = "visitType") VisitTypeDTO visitTypeDTO, String dateString, String doctorName,
			String city, Model model) {
		LocalDate date = LocalDate.parse(dateString);
		doctorsCalendarDTO = doctorsCalendarService.findByDoctorAndDateAndTypeAndCity(doctorName, date, visitTypeDTO,
				city);
		model.addAttribute("doctorCalendar", doctorsCalendarDTO);
		System.out.println(doctorsCalendarDTO);
		return "redirect:/homeAddVisit.html";
	}

	@GetMapping("/addVisit.html")
	public String addVisit(HttpServletRequest request, Model model) {
		List<LocalDate> dates = doctorsCalendarService.getDates(doctorsCalendarDTO);
		model.addAttribute("doctorCalendar", doctorsCalendarDTO);
		model.addAttribute("dates", dates);
		return "addVisit.html";
	}

	@GetMapping("/homeAddVisit.html")
	public String addVisitHome(HttpServletRequest request, Model model) {
		
		return "redirect:/addVisit.html";
	}

	@PostMapping("/addVisit/add")
	public String addNewVisit(HttpServletRequest request,
			String docorIdx) {
		DoctorsCalendarDTO doctorCalendar = doctorsCalendarDTO.get(Integer.valueOf(docorIdx));
		String login = currentUserNameSimple(request);
		scheduledVisitService.addNewEvent(login, doctorCalendar);
		doctorsCalendarService.addPatientToDoctorCalendar(login, doctorCalendar);
		return "redirect:/addVisit.html";
	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		return login;
	}

}
