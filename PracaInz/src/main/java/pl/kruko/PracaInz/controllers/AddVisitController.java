package pl.kruko.PracaInz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.DoctorDTO;
import dataTransferObjects.VisitTypeDTO;
import pl.kruko.PracaInz.models.Type;
import pl.kruko.PracaInz.service.DoctorService;
import pl.kruko.PracaInz.service.ScheduledVisitService;
import pl.kruko.PracaInz.service.VisitTypeService;

@Controller
public class AddVisitController {

	@Autowired
	ScheduledVisitService scheduledVisitService;

	@Autowired
	DoctorService doctorService;

	@Autowired
	VisitTypeService visitTypeService;
	List<VisitTypeDTO> visitsTypesDTO;

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
			@ModelAttribute(value = "visitType") VisitTypeDTO visitTypeDTO, String date, String doctorName,
			String city) {
		System.out.println(doctorName);
		System.out.println(visitTypeDTO);
		System.out.println(date);
		System.out.println(city);
		return "redirect:/searchDoctor.html";
	}

}
