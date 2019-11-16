package pl.kruko.PracaInz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.Patient;
import pl.kruko.PracaInz.models.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
	List<Visit> findByPatient(Patient patient);

}
