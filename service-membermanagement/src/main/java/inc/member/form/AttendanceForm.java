package inc.member.form;

import java.util.ArrayList;
import java.util.List;

import inc.member.enums.WorshipServiceEnum;

public class AttendanceForm {

	private String id = "";
	private String date = "";
	private String startDate = "";
	private String endDate = "";
	private String status = WorshipServiceEnum.NA.toString();
	
	private List<AttendanceForm> absents = new ArrayList<AttendanceForm>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AttendanceForm> getAbsents() {
		return absents;
	}
	public void setAbsents(List<AttendanceForm> absents) {
		this.absents = absents;
	}

}
