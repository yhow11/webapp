package com.inctool.common.model;

import java.util.Date;

import com.inctool.common.annotation.MapUtilField;
import com.inctool.common.enums.WorshipServiceEnum;

public class DateModel {
	
	@MapUtilField(name="id")
	private String id;
	@MapUtilField(name="date")
	private Date date;
	@MapUtilField(name="startDate")
	private Date startDate;
	@MapUtilField(name="endDate")
	private Date endDate;
	@MapUtilField(name="status")
	private WorshipServiceEnum status = WorshipServiceEnum.NA; 
	
	
	public WorshipServiceEnum getStatus() {
		return status;
	}
	public void setStatus(WorshipServiceEnum status) {
		this.status = status;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
