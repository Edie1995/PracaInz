package pl.kruko.PracaInz.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dataTransferObjects.MedicamentDTO;
import dataTransferObjects.PatientDTO;
import dataTransferObjects.ScheduledVisitDTO;
import dataTransferObjects.SymptomDTO;
import dataTransferObjects.VisitDTO;
import pl.kruko.PracaInz.service.DiagnosisService;
import pl.kruko.PracaInz.service.MedicamentService;
import pl.kruko.PracaInz.service.PatientSymptomService;
import pl.kruko.PracaInz.service.PatientsMedicamentService;
import pl.kruko.PracaInz.service.SymptomService;
import pl.kruko.PracaInz.service.VisitService;

@Controller
public class ChosenPatientController {

	private VisitService visitService;
	private PatientsMedicamentService patientsMedicamentService;
	private PatientSymptomService patientSymptomService;
	private DiagnosisService diagnosisService;
	private SymptomService symptomService;
	private MedicamentService medicamentService;
	
	private ScheduledVisitDTO scheduledVisit;
	private VisitDTO visitDTO;

	
	@Autowired
	public ChosenPatientController(VisitService visitService,
			PatientsMedicamentService patientsMedicamentService, PatientSymptomService patientSymptomService,
			DiagnosisService diagnosisService, SymptomService symptomService, MedicamentService medicamentService) {
		super();
		this.visitService = visitService;
		this.patientsMedicamentService = patientsMedicamentService;
		this.patientSymptomService = patientSymptomService;
		this.diagnosisService = diagnosisService;
		this.symptomService = symptomService;
		this.medicamentService = medicamentService;
	}

	
	@GetMapping("/chosenPatient.html")
	public String getPatient(HttpServletRequest request, Model model) {
		List<SymptomDTO> symptomsDictionary = symptomService.findAll();
		model.addAttribute("symptomsDic", symptomsDictionary);
		model.addAttribute("symptomName", new SymptomDTO());
		List<MedicamentDTO> medicamentDictionary = medicamentService.findAll();
		model.addAttribute("medicamentDic", medicamentDictionary);
		model.addAttribute("medicament", new MedicamentDTO());
		PatientDTO patientDTO = scheduledVisit.getPatient();
		model.addAttribute("patient", patientDTO);
		model.addAttribute("medicaments", patientsMedicamentService.findAllByPatientDTO(patientDTO));
		model.addAttribute("symptoms", patientSymptomService.findByPatientDTO(patientDTO));
		model.addAttribute("diagnosis", diagnosisService.findByVisitPatient(patientDTO));
		model.addAttribute("visit", visitDTO);
		return "chosenPatient.html";
	}

	@GetMapping("/homechosenPatient.html")
	public String getPatientData(HttpServletRequest request, Model model) {
		scheduledVisit = (ScheduledVisitDTO) model.getAttribute("visit");
		return "redirect:/chosenPatient.html";
	}

	@PostMapping("/addToVisit")
	public String addToVisit(HttpServletRequest request, Model model) {
		String login = currentUserNameSimple(request);
		visitDTO = visitService.createVisitDTO(login, scheduledVisit);
		visitDTO.setId(visitService.save(visitDTO));
		return "redirect:/chosenPatient.html";
	}

	@PostMapping("/chosenPatient/addSymptom")
	public String addSymptomToVisit(@ModelAttribute("symptomName") SymptomDTO symptom, String date) {
		patientSymptomService.saveSymptomByVisit(symptom, visitDTO, date);
		return "redirect:/chosenPatient.html";

	}

	@PostMapping("/chosenPatient/addMedicament")
	public String addMedicamentToVisit(@ModelAttribute("medicament") MedicamentDTO medicamentDTO, int dosage,
			int frequency, String date) {
		patientsMedicamentService.save(medicamentDTO, dosage, frequency, visitDTO, date);
		return "redirect:/chosenPatient.html";

	}

	@PostMapping("/chosenPatient/addDiagnosis")
	public String addDiagnosisToVisit(String name, String details) {
		diagnosisService.save(name, details, visitDTO);
		return "redirect:/chosenPatient.html";

	}

	public String currentUserNameSimple(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String login = principal.getName();
		System.out.println(login);
		return login;
	}

}
