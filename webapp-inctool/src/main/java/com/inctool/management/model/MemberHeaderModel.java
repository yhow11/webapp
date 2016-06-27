package com.inctool.management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.inctool.common.model.ActionModel;
import com.inctool.common.model.DateModel;
import com.inctool.common.model.PersonModel;
import com.inctool.management.enums.MemberEnum;

@Document
public class MemberHeaderModel extends PersonModel{

	@Id
	private String id;
	private String dcode;
	private String lcode;
	private String area;
	private String group;
	private String reference;
	private String address;
	private Date createdDate;
	private MemberEnum status = MemberEnum.GUEST; 
	
	private List<DateModel> r310 = new ArrayList<DateModel>();
	private List<DateModel> r305 = new ArrayList<DateModel>();
	private List<DateModel> r309 = new ArrayList<DateModel>();
	private List<ActionModel> actions = new ArrayList<ActionModel>();
	
	
	public MemberEnum getStatus() {
		return status;
	}
	public void setStatus(MemberEnum status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDcode() {
		return dcode;
	}
	public void setDcode(String dcode) {
		this.dcode = dcode;
	}
	public String getLcode() {
		return lcode;
	}
	public void setLcode(String lcode) {
		this.lcode = lcode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public List<DateModel> getR310() {
		return r310;
	}
	public void setR310(List<DateModel> r310) {
		this.r310 = r310;
	}
	public List<DateModel> getR305() {
		return r305;
	}
	public void setR305(List<DateModel> r305) {
		this.r305 = r305;
	}
	public List<DateModel> getR309() {
		return r309;
	}
	public void setR309(List<DateModel> r309) {
		this.r309 = r309;
	}
	
	public List<ActionModel> getActions() {
		return actions;
	}
	public void setActions(List<ActionModel> actions) {
		this.actions = actions;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
