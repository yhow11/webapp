package inc.member.enums;

public enum WorkerOptionEnum {

	REMOVE("Remove"),
	EDIT("Edit");
	
	private String name;
	
	WorkerOptionEnum(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
