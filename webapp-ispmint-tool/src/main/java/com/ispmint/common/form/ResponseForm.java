package com.ispmint.common.form;

import java.util.ArrayList;
import java.util.List;

public class ResponseForm<T> {

	private boolean status;
	private String message;
	private List<T> data = new ArrayList<T>();
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
}
