package com.inctool.management.object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inctool.common.form.ActionForm;
import com.inctool.common.form.DateForm;
import com.inctool.common.form.PersonForm;
import com.inctool.management.enums.MemberEnum;

public class MemberForm extends PersonForm {

	private String id;
	private String dcode;
	private String lcode;
	private String area;
	private String group;
	private String reference;
	private String address;
	private String present = "0";
	private String absent = "0";
	private String status = MemberEnum.GUEST.toString();
	private Date createdDate;
	private Date completionDate;
	private List<DateForm> r310 = new ArrayList<DateForm>();
	private List<DateForm> r305 = new ArrayList<DateForm>();
	private List<DateForm> r309 = new ArrayList<DateForm>();
	private List<ActionForm> actions = new ArrayList<ActionForm>();
	
	
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public List<DateForm> getR310() {
		return r310;
	}
	public void setR310(List<DateForm> r310) {
		this.r310 = r310;
	}
	public List<DateForm> getR305() {
		return r305;
	}
	public void setR305(List<DateForm> r305) {
		this.r305 = r305;
	}
	public List<DateForm> getR309() {
		return r309;
	}
	public void setR309(List<DateForm> r309) {
		this.r309 = r309;
	}
	
	public List<ActionForm> getActions() {
		return actions;
	}
	public void setActions(List<ActionForm> actions) {
		this.actions = actions;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
