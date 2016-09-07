package com.inctool.common.form;

import common.mapper.annotation.MapUtilField;

public class OptionForm {
	
	@MapUtilField
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
