package inc.member.form;

import java.util.ArrayList;
import java.util.List;

import inc.member.enums.MemberEnum;

public class MemberForm{

	private String id = "";
	private String reference = "";
	private String name = "";
	private String dcode = "";
	private String lcode = "";
	private String area = "";
	private String group = "";
	private List<OptionForm> options = new ArrayList<OptionForm>();
	
	private String status = MemberEnum.GUEST.toString();
	private String createdDate = "";
	
	
	private SummaryForm dkSummary;
	private SummaryForm skSummary;
	private SummaryForm wsSummary;
	
	private List<AttendanceForm> dkAttendance = new ArrayList<AttendanceForm>();
	private List<AttendanceForm> skAttendance = new ArrayList<AttendanceForm>();
	private List<AttendanceForm> wsAttendance = new ArrayList<AttendanceForm>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<OptionForm> getOptions() {
		return options;
	}
	public void setOptions(List<OptionForm> options) {
		this.options = options;
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
	public List<AttendanceForm> getDkAttendance() {
		return dkAttendance;
	}
	public void setDkAttendance(List<AttendanceForm> dkAttendance) {
		this.dkAttendance = dkAttendance;
	}
	public List<AttendanceForm> getSkAttendance() {
		return skAttendance;
	}
	public void setSkAttendance(List<AttendanceForm> skAttendance) {
		this.skAttendance = skAttendance;
	}
	public List<AttendanceForm> getWsAttendance() {
		return wsAttendance;
	}
	public void setWsAttendance(List<AttendanceForm> wsAttendance) {
		this.wsAttendance = wsAttendance;
	}
	public SummaryForm getDkSummary() {
		return dkSummary;
	}
	public void setDkSummary(SummaryForm dkSummary) {
		this.dkSummary = dkSummary;
	}
	public SummaryForm getSkSummary() {
		return skSummary;
	}
	public void setSkSummary(SummaryForm skSummary) {
		this.skSummary = skSummary;
	}
	public SummaryForm getWsSummary() {
		return wsSummary;
	}
	public void setWsSummary(SummaryForm wsSummary) {
		this.wsSummary = wsSummary;
	}
	
}
