package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.object.QueryHelperParam;

@PhoenixTable(table="visitorLog")
public class VisitorLogModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	private Long id;
	@PhoenixColumn(type="VARCHAR(255)")
	private String webFP;
	@PhoenixColumn(type="VARCHAR(255)")
	private String deviceFP;
	@PhoenixColumn(type="VARCHAR(255)")
	private String leadID;
	@PhoenixColumn(type="BIGINT")
	private Long timeStamp;
	@PhoenixColumn(type="VARCHAR(255)")
	private String type;
	@PhoenixColumn(type="VARCHAR(255)")
	private String url;
	@PhoenixColumn(type="VARCHAR(255)")
	private String title;
	@PhoenixColumn(type="VARCHAR(255)")
	private String sessionID;
	@PhoenixColumn(type="VARCHAR(255)")
	private String elapsedTime;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
}
