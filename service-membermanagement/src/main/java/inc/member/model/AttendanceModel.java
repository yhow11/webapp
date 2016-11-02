package inc.member.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;



public class AttendanceModel {
	
	@Field
	private String id;
	@Field
	private Date date;
	@Field
	private Date startDate;
	@Field
	private Date endDate;
	@Field
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
