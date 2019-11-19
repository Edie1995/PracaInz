package pl.kruko.PracaInz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kruko.PracaInz.models.VisitType;
import pl.kruko.PracaInz.repo.VisitTypeRepository;

@Service
public class VisitTypeService {

	private VisitTypeRepository visitTypeRepository;

	@Autowired
	public VisitTypeService(VisitTypeRepository visitTypeRepository) {
		super();
		this.visitTypeRepository = visitTypeRepository;
	}

	public List<VisitType> findByType(int type) {
		List<VisitType> visitTypes = visitTypeRepository.findByType(type);
		return visitTypes;
	}

	public VisitType findByName(String name) {
		VisitType visitType = visitTypeRepository.findByName(name);
		return visitType;
	}

}
