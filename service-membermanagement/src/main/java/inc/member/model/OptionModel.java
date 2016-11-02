package inc.member.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class OptionModel {
	
	@Field
	private String name;
	@Field
	private String value;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
