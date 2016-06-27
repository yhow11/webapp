package com.inctool.chart.form;

import java.util.List;

public class SeriesForm {
	
	private String name;
	private List<?> data;
	
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
