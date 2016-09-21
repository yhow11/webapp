package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.object.QueryHelperParam;

@PhoenixTable(table="webEvent")
public class WebEventModel implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	protected Long id;

	@PhoenixColumn(type="VARCHAR(100)")
	protected String anonymousVisitorID;

	@PhoenixColumn(type="VARCHAR(100)")
	protected String browserFPID;

	@PhoenixColumn(type="VARCHAR(100)")
	protected String deviceFPID;

	@PhoenixColumn(type="BIGINT")
	protected Long timeStamp;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String type;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String url;
	
	@PhoenixColumn(type="VARCHAR(100)")
	protected String title;

	@PhoenixColumn(type="VARCHAR(100)")
	protected String elapsedTime;
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

}
