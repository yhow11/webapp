package service.membermanagement.model;

import java.util.Date;

import common.mapper.annotation.MapUtilField;


public class DateModel {
	
	@MapUtilField
	private String id;
	@MapUtilField
	private Date date;
	@MapUtilField
	private Date startDate;
	@MapUtilField
	private Date endDate;
	@MapUtilField
	private String status; 
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
