package inc.member.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="INCMemberCollection")
public class MemberModel {

	@Id
	private String id;
	@Field
	private String reference;
	@Field
	protected String name;
	@Field
	private String dcode;
	@Field
	private String lcode;
	@Field
	private String area;
	@Field
	private String group;
	@Field
	private Date createdDate;
	@Field
	private String status; 

	private List<OptionModel> options = new ArrayList<OptionModel>();
	
	private SummaryModel dkSummary;
	private SummaryModel skSummary;
	private SummaryModel wsSummary;
	
	private List<AttendanceModel> dkAttendance = new ArrayList<AttendanceModel>();
	private List<AttendanceModel> skAttendance = new ArrayList<AttendanceModel>();
	private List<AttendanceModel> wsAttendance = new ArrayList<AttendanceModel>();
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OptionModel> getOptions() {
		return options;
	}
	public void setOptions(List<OptionModel> options) {
		this.options = options;
	}
	public SummaryModel getDkSummary() {
		return dkSummary;
	}
	public void setDkSummary(SummaryModel dkSummary) {
		this.dkSummary = dkSummary;
	}
	public SummaryModel getSkSummary() {
		return skSummary;
	}
	public void setSkSummary(SummaryModel skSummary) {
		this.skSummary = skSummary;
	}
	public SummaryModel getWsSummary() {
		return wsSummary;
	}
	public void setWsSummary(SummaryModel wsSummary) {
		this.wsSummary = wsSummary;
	}
	public List<AttendanceModel> getDkAttendance() {
		return dkAttendance;
	}
	public void setDkAttendance(List<AttendanceModel> dkAttendance) {
		this.dkAttendance = dkAttendance;
	}
	public List<AttendanceModel> getSkAttendance() {
		return skAttendance;
	}
	public void setSkAttendance(List<AttendanceModel> skAttendance) {
		this.skAttendance = skAttendance;
	}
	public List<AttendanceModel> getWsAttendance() {
		return wsAttendance;
	}
	public void setWsAttendance(List<AttendanceModel> wsAttendance) {
		this.wsAttendance = wsAttendance;
	}
	
	
}

