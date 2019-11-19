package pl.kruko.PracaInz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Specialization;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	Optional<Doctor> findByLogin (String login);
	
	@Query("SELECT d FROM Doctor d WHERE (:name is null or d.name = name) and (:specialization is null or d.specialization = :specialization) and (:institution is null oraz d.institution = :institution)")
	List<Doctor> findByNameAndSpecializationAndCityAndInstitution(String name, Specialization specialization, Institution institution);
	
}
