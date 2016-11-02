package usertracker.browser.visitorlog.form;

public class VisitorLogForm{
	
	private String id;
	private String webFP;
	private String deviceFP;
	private String leadID;
	private String timeStamp;
	private String type;
	private String url;
	private String title;
	private String sessionID;
	private String elapsedTime;
	private String country;
	
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getLeadID() {
		return leadID;
	}
	public void setLeadID(String leadID) {
		this.leadID = leadID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWebFP() {
		return webFP;
	}
	public void setWebFP(String webFP) {
		this.webFP = webFP;
	}
	public String getDeviceFP() {
		return deviceFP;
	}
	public void setDeviceFP(String deviceFP) {
		this.deviceFP = deviceFP;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
