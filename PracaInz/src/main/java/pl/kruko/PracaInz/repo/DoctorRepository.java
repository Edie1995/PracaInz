package pl.kruko.PracaInz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.Institution;
import pl.kruko.PracaInz.models.Specialization;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	Optional<Doctor> findById (Long id);
	
	@Query("SELECT d FROM Doctor d WHERE (:lastName is null or d.lastName = lastName) and (:specialization is null or d.specialization = :specialization) and (:institutions is null or d.institutions = :institutions)")
	List<Doctor> findByNameAndSpecializationAndCityAndInstitution(String lastName, Specialization specialization, List<Institution> institutions);
	List<Doctor> findByLastName(String lastName);
	List<Doctor> findAll();
}
