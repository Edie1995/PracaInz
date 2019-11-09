package pl.kruko.PracaInz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
	Visit findByPatient(Patient patient);

}
