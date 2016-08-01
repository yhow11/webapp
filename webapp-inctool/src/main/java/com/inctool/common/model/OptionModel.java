package com.inctool.common.model;

import com.inctool.common.annotation.MapUtilField;

public class OptionModel {
	
	@MapUtilField(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
