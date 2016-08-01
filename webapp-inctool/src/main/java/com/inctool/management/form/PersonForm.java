package com.inctool.management.form;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.annotation.MapUtilField;
import com.inctool.common.form.OptionForm;

public class PersonForm {
	
	@MapUtilField(name="name")
	protected String name;
	@MapUtilField(name="address")
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
