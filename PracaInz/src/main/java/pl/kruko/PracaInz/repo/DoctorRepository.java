package pl.kruko.PracaInz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	
	
}
