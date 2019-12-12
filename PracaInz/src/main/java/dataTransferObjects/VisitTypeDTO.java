package dataTransferObjects;

import pl.kruko.PracaInz.models.Type;

public class VisitTypeDTO {

	private Long id;
	private String name;
	private Type type;
	
	public VisitTypeDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "name=" + name + " id=" + id;
	}
	
	
	
	
}
