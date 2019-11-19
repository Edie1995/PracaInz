package pl.kruko.PracaInz.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.Doctor;
import pl.kruko.PracaInz.models.DoctorsCalendar;

public interface DoctorsCalendarRepository extends JpaRepository<DoctorsCalendar, Long>{
	
	List<DoctorsCalendar> findByDoctor(Doctor doctor);
	List<DoctorsCalendar> findByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);
	List<DoctorsCalendar> findByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime startDate, LocalDateTime endDate);

}
