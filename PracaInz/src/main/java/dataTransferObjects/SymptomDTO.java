package dataTransferObjects;

public class SymptomDTO {

	private Long id;
	private String name;
	
	public SymptomDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public SymptomDTO(String name) {
		super();
		this.name = name;
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
	
	
}
