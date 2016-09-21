package usertracker.enums;

public enum WebEventTypeEnum {

	VISITED("visited"),
	LEAVED("leaved"),
	CLICKED("clicked"),
	HOVER("hover");
	
	private String type;
	
	WebEventTypeEnum(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
