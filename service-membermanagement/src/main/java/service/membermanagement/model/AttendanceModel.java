package service.membermanagement.model;

import java.util.Date;

import common.mapper.annotation.MapUtilField;

public class AttendanceModel {
	
	@MapUtilField
	protected String present;
	@MapUtilField
	protected String absent;
	@MapUtilField
	protected String left;
	@MapUtilField
	protected Date completionDate;
	
	public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
	public String getAbsent() {
		return absent;
	}
	public void setAbsent(String absent) {
		this.absent = absent;
	}
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	
	
	
}
