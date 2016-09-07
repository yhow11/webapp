package com.inctool.management.form;

import common.mapper.annotation.MapUtilField;

public class AttendanceForm {
	
	@MapUtilField
	protected String present;
	@MapUtilField
	protected String absent;
	@MapUtilField
	protected String left;
	@MapUtilField
	protected String completionDate;
	
	
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
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
	
	
	
}
