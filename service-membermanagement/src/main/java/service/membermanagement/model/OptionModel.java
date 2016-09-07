package service.membermanagement.model;

import common.mapper.annotation.MapUtilField;

public class OptionModel {
	
	@MapUtilField
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
