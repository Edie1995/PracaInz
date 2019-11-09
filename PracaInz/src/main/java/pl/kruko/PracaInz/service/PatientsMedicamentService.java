package pl.kruko.PracaInz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.Medicament;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.PatientsMedicament;
import pl.kruko.PracaInz.models.Status;
import pl.kruko.PracaInz.repo.PatientsMedicamentRepository;

@Service
public class PatientsMedicamentService {

	private Patient patient;
	@Autowired
	private PatientsMedicamentRepository patientsMedicamentRepository;
	
	
	private PatientsMedicament findById(Long id) {
		return patientsMedicamentRepository.findById(id).orElse(new PatientsMedicament());
	}
	
//	private List<PatientsMedicament> findByStatusAndPatient(Status status, Patient patient){
//		return patientsMedicamentRepository.findByStatusAndPatient(status, patient);
//	}
	
//	private List<PatientsMedicament> findByMedicamentsAndPatient(Medicament medicament, Patient patient){
//		//return patientsMedicamentRepository.findByMedicamentAndPatient(medicament, patient);
//	}
	
	private List<PatientsMedicament> findAll(){
		return patientsMedicamentRepository.findAll();
	}
	
}
