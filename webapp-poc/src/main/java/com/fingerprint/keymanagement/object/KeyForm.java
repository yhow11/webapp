package com.fingerprint.keymanagement.object;

import java.util.ArrayList;
import java.util.List;

public class KeyForm {

	private String key;
	private List<ValueForm> values = new ArrayList<>();
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<ValueForm> getValues() {
		return values;
	}
	public void setValues(List<ValueForm> values) {
		this.values = values;
	}
	
	
}
