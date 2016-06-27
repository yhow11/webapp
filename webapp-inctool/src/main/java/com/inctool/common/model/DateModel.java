package com.inctool.common.model;

import java.util.Date;

import com.inctool.management.enums.WorshipServiceEnum;

public class DateModel {
	
	private String id;
	private Date date;
	private Date weekStartDate;
	private Date weekEndDate;
	private WorshipServiceEnum worshipServiceStatus = WorshipServiceEnum.NA; 
	
	public WorshipServiceEnum getWorshipServiceStatus() {
		return worshipServiceStatus;
	}
	public void setWorshipServiceStatus(WorshipServiceEnum worshipServiceStatus) {
		this.worshipServiceStatus = worshipServiceStatus;
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
	
	
	
}
