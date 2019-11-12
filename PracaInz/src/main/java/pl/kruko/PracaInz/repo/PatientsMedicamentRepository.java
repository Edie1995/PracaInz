package pl.kruko.PracaInz.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.PatientsMedicament;

public interface PatientsMedicamentRepository extends JpaRepository<PatientsMedicament, Long> {

	Optional<PatientsMedicament> findById(Long id);

	//List<PatientsMedicament> findByStatusAndPatient(Status status, Patient patient);
	
	//List<PatientsMedicament> findByMedicamentAndPatient(Medicament medicament, Patient patient);
	
}
