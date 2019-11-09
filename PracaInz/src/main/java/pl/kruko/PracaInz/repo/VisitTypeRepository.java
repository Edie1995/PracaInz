package pl.kruko.PracaInz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kruko.PracaInz.models.VisitType;

public interface VisitTypeRepository extends JpaRepository<VisitType, Long>{

	VisitType findByName(String name);
}
