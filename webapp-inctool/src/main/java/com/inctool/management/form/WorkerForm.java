package com.inctool.management.form;

import com.inctool.management.enums.MemberEnum;

import common.mapper.annotation.MapUtilField;

public class WorkerForm extends PersonForm{

	@MapUtilField
	private String id;
	@MapUtilField
	private String status = MemberEnum.GUEST.toString();
	@MapUtilField
	private String createdDate;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
