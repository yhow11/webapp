package com.inctool.common.form;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.enums.WorshipServiceEnum;

import common.mapper.annotation.MapUtilField;

public class DateForm {

	@MapUtilField
	private String id;
	@MapUtilField
	private String date;
	@MapUtilField
	private String startDate;
	@MapUtilField
	private String endDate;
	@MapUtilField
	private String status = WorshipServiceEnum.NA.toString();
	
	private List<DateForm> absents = new ArrayList<DateForm>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<DateForm> getAbsents() {
		return absents;
	}
	public void setAbsents(List<DateForm> absents) {
		this.absents = absents;
	}

}
