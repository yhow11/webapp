package com.inctool.common.model;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.annotation.MapUtilField;

public class PersonModel {

	@MapUtilField(name="name")
	protected String name;
	@MapUtilField(name="address")
	protected String address;
	
	private List<OptionModel> options = new ArrayList<OptionModel>();
	
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

	public List<OptionModel> getOptions() {
		return options;
	}

	public void setOptions(List<OptionModel> options) {
		this.options = options;
	}
	
}
