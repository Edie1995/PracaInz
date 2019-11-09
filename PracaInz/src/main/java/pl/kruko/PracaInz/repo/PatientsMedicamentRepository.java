package pl.kruko.PracaInz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.Medicament;
import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.PatientsMedicament;
import pl.kruko.PracaInz.models.Status;

public interface PatientsMedicamentRepository extends JpaRepository<PatientsMedicament, Long> {

	Optional<PatientsMedicament> findById(Long id);

	//List<PatientsMedicament> findByStatusAndPatient(Status status, Patient patient);
	
	//List<PatientsMedicament> findByMedicamentAndPatient(Medicament medicament, Patient patient);
	
}
