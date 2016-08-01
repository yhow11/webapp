package com.inctool.management.form;

import java.util.ArrayList;
import java.util.List;

import com.inctool.common.annotation.MapUtilField;
import com.inctool.common.form.DateForm;
import com.inctool.management.enums.MemberEnum;

public class MemberForm extends PersonForm {

	@MapUtilField(name="id")
	private String id;
	@MapUtilField(name="reference")
	private String reference;
	@MapUtilField(name="status")
	private String status = MemberEnum.GUEST.toString();
	@MapUtilField(name="createdDate")
	private String createdDate;
	
	@MapUtilField(name="dcode")
	private String dcode;
	@MapUtilField(name="lcode")
	private String lcode;
	@MapUtilField(name="area")
	private String area;
	@MapUtilField(name="group")
	private String group;
	
	private AttendanceForm r310Attendance;
	private AttendanceForm r305Attendance;
	private AttendanceForm r309Attendance;
	
	private List<DateForm> r310 = new ArrayList<DateForm>();
	private List<DateForm> r305 = new ArrayList<DateForm>();
	private List<DateForm> r309 = new ArrayList<DateForm>();
	
	
	
	public AttendanceForm getR310Attendance() {
		return r310Attendance;
	}
	public void setR310Attendance(AttendanceForm r310Attendance) {
		this.r310Attendance = r310Attendance;
	}
	public AttendanceForm getR305Attendance() {
		return r305Attendance;
	}
	public void setR305Attendance(AttendanceForm r305Attendance) {
		this.r305Attendance = r305Attendance;
	}
	public AttendanceForm getR309Attendance() {
		return r309Attendance;
	}
	public void setR309Attendance(AttendanceForm r309Attendance) {
		this.r309Attendance = r309Attendance;
	}
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
}
