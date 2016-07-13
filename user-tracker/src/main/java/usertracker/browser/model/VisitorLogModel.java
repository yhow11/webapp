package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.PhoenixFieldAnnotation;
import helper.phoenix.annotation.PhoenixTableAnnotation;

@PhoenixTableAnnotation(table="visitorLog")
public class VisitorLogModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PhoenixFieldAnnotation(type="VARCHAR(255)", primary=true)
	private String id;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String webFP;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String deviceFP;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String leadID;
	@PhoenixFieldAnnotation(type="BIGINT")
	private Long timeStamp;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String type;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String url;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String title;
	@PhoenixFieldAnnotation(type="VARCHAR(255)")
	private String sessionID;
	
	
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
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
