package service.membermanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import common.mapper.annotation.MapUtilField;

@Document(collection="INCMemberCollection")
public class INCMemberModel extends PersonModel {

	@Id
	@MapUtilField
	private String id;
	@Field
	@MapUtilField
	private String dcode;
	@Field
	@MapUtilField
	private String lcode;
	@Field
	@MapUtilField
	private String area;
	@Field
	@MapUtilField
	private String group;
	@Field
	@MapUtilField
	private String reference;
	@Field
	@MapUtilField
	private Date createdDate;
	@Field
	@MapUtilField
	private String status; 
	
	
	private AttendanceModel r310Attendance;
	private AttendanceModel r305Attendance;
	private AttendanceModel r309Attendance;
	
	private List<DateModel> r310 = new ArrayList<DateModel>();
	private List<DateModel> r305 = new ArrayList<DateModel>();
	private List<DateModel> r309 = new ArrayList<DateModel>();
	
	
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
	
	
}

