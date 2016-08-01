package com.inctool.common.form;

import com.inctool.common.annotation.MapUtilField;

public class OptionForm {
	
	@MapUtilField(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
