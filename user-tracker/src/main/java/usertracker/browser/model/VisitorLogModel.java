package usertracker.browser.model;

import java.io.Serializable;

import hbase.annotation.HBaseColumnAnnotation;
import hbase.annotation.HBaseTableAnnotation;

@HBaseTableAnnotation(tablename="visitorLog")
public class VisitorLogModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@HBaseColumnAnnotation(name="id", family="metadata")
	private String id;
	@HBaseColumnAnnotation(name="fp", family="metadata")
	private String fp;
	@HBaseColumnAnnotation(name="webFP", family="metadata")
	private String webFP;
	@HBaseColumnAnnotation(name="deviceFP", family="metadata")
	private String deviceFP;
	@HBaseColumnAnnotation(name="leadID", family="metadata")
	private String leadID;
	@HBaseColumnAnnotation(name="timeStamp", family="metadata")
	private String timeStamp;
	@HBaseColumnAnnotation(name="timeStampStr", family="metadata")
	private String timeStampStr;
	@HBaseColumnAnnotation(name="type", family="metadata")
	private String type;
	@HBaseColumnAnnotation(name="url", family="metadata")
	private String url;
	@HBaseColumnAnnotation(name="title", family="metadata")
	private String title;
	@HBaseColumnAnnotation(name="sessionID", family="metadata")
	private String sessionID;
	private Long linkCount;
	
	
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getTimeStampStr() {
		return timeStampStr;
	}
	public void setTimeStampStr(String timeStampStr) {
		this.timeStampStr = timeStampStr;
	}
	public String getLeadID() {
		return leadID;
	}
	public void setLeadID(String leadID) {
		this.leadID = leadID;
	}
	public Long getLinkCount() {
		return linkCount;
	}
	public void setLinkCount(Long linkCount) {
		this.linkCount = linkCount;
	}
	public String getFp() {
		return fp;
	}
	public void setFp(String fp) {
		this.fp = fp;
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
