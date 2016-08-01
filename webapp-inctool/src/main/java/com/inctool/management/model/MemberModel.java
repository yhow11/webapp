package com.inctool.management.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.inctool.common.annotation.MapUtilField;
import com.inctool.common.model.DateModel;
import com.inctool.common.model.PersonModel;
import com.inctool.management.enums.MemberEnum;

@Document
public class MemberModel extends PersonModel{

	@MapUtilField(name="id")
	@Id
	private String id;
	@MapUtilField(name="dcode")
	private String dcode;
	@MapUtilField(name="lcode")
	private String lcode;
	@MapUtilField(name="area")
	private String area;
	@MapUtilField(name="group")
	private String group;
	@MapUtilField(name="reference")
	private String reference;
	@MapUtilField(name="createdDate")
	private Date createdDate;
	@MapUtilField(name="status")
	private MemberEnum status = MemberEnum.GUEST; 
	
	
	private AttendanceModel r310Attendance;
	private AttendanceModel r305Attendance;
	private AttendanceModel r309Attendance;
	
	private List<DateModel> r310 = new ArrayList<DateModel>();
	private List<DateModel> r305 = new ArrayList<DateModel>();
	private List<DateModel> r309 = new ArrayList<DateModel>();
	
	
	public AttendanceModel getR310Attendance() {
		return r310Attendance;
	}
	public void setR310Attendance(AttendanceModel r310Attendance) {
		this.r310Attendance = r310Attendance;
	}
	public AttendanceModel getR305Attendance() {
		return r305Attendance;
	}
	public void setR305Attendance(AttendanceModel r305Attendance) {
		this.r305Attendance = r305Attendance;
	}
	public AttendanceModel getR309Attendance() {
		return r309Attendance;
	}
	public void setR309Attendance(AttendanceModel r309Attendance) {
		this.r309Attendance = r309Attendance;
	}
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
	
}
