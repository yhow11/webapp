package com.inctool.management.form;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.form.OptionForm;

import common.mapper.annotation.MapUtilField;

public class PersonForm {
	
	@MapUtilField
	protected String name;
	@MapUtilField
	protected String address;
	private List<OptionForm> options = new ArrayList<OptionForm>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OptionForm> getOptions() {
		return options;
	}

	public void setOptions(List<OptionForm> options) {
		this.options = options;
	}
	
	
}
