package usertracker.browser.model;

import java.io.Serializable;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.object.PaginationQuery;

@PhoenixTable(table="webEvent")
public class WebEventModel extends PaginationQuery implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	@PhoenixID
	@PhoenixColumn(type="VARCHAR(100)")
	protected String id;

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
