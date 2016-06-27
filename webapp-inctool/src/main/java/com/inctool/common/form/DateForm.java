package com.inctool.common.form;

import java.util.Date;

import com.inctool.management.enums.WorshipServiceEnum;

public class DateForm {
	
	private String id;
	private Date date;
	private String datepicker;
	private Date weekStartDate;
	private Date weekEndDate;
	private String worshipServiceStatus = WorshipServiceEnum.NA.toString(); 
	
public String getWorshipServiceStatus() {
	return worshipServiceStatus;
}
public void setWorshipServiceStatus(String worshipServiceStatus) {
	this.worshipServiceStatus = worshipServiceStatus;
}
public Date getWeekStartDate() {
		return weekStartDate;
	}
	public void setWeekStartDate(Date weekStartDate) {
		this.weekStartDate = weekStartDate;
	}
	public Date getWeekEndDate() {
		return weekEndDate;
	}
	public void setWeekEndDate(Date weekEndDate) {
		this.weekEndDate = weekEndDate;
	}
public String getDatepicker() {
	return datepicker;
}
public void setDatepicker(String datepicker) {
	this.datepicker = datepicker;
}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
