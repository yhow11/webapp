package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.PhoenixFieldAnnotation;
import helper.phoenix.annotation.PhoenixTableAnnotation;

@PhoenixTableAnnotation(table="webEvent")
public class WebEventModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PhoenixFieldAnnotation(type="VARCHAR(100)", primary=true)
	private String id;

	@PhoenixFieldAnnotation(type="VARCHAR(100)")
	private String anonymousVisitorID;

	@PhoenixFieldAnnotation(type="VARCHAR(100)")
	private String browserFPID;

	@PhoenixFieldAnnotation(type="VARCHAR(100)")
	private String deviceFPID;

	@PhoenixFieldAnnotation(type="BIGINT")
	private Long timeStamp;
	
	@PhoenixFieldAnnotation(type="VARCHAR(100)")
	private String type;
	
	@PhoenixFieldAnnotation(type="VARCHAR(100)")
	private String url;
	
	@PhoenixFieldAnnotation(type="VARCHAR(100)")
	private String title;

	
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnonymousVisitorID() {
		return anonymousVisitorID;
	}

	public void setAnonymousVisitorID(String anonymousVisitorID) {
		this.anonymousVisitorID = anonymousVisitorID;
	}

	public String getBrowserFPID() {
		return browserFPID;
	}

	public void setBrowserFPID(String browserFPID) {
		this.browserFPID = browserFPID;
	}

	public String getDeviceFPID() {
		return deviceFPID;
	}

	public void setDeviceFPID(String deviceFPID) {
		this.deviceFPID = deviceFPID;
	}

}
