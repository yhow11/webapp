package com.inctool.common.form;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.annotation.MapUtilField;
import com.inctool.common.enums.WorshipServiceEnum;

public class DateForm {

	@MapUtilField(name="id")
	private String id;
	@MapUtilField(name="date")
	private String date;
	@MapUtilField(name="startDate")
	private String startDate;
	@MapUtilField(name="endDate")
	private String endDate;
	@MapUtilField(name="status")
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
