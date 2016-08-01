package com.inctool.management.model;

import java.util.Date;

import com.inctool.common.annotation.MapUtilField;

public class AttendanceModel {
	
	@MapUtilField(name="present")
	protected String present;
	@MapUtilField(name="absent")
	protected String absent;
	@MapUtilField(name="left")
	protected String left;
	@MapUtilField(name="completionDate")
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
