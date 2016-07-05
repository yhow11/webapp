package usertracker.browser.model;

import java.io.Serializable;

import hbase.annotation.HBaseColumnAnnotation;
import hbase.annotation.HBaseTableAnnotation;

@HBaseTableAnnotation(tablename = "webEvent")
public class WebEventModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@HBaseColumnAnnotation(name = "id", family = "metadata")
	private String id;

	@HBaseColumnAnnotation(name = "anonymousVisitorID", family = "metadata")
	private String anonymousVisitorID;

	@HBaseColumnAnnotation(name = "browserFPID", family = "metadata")
	private String browserFPID;

	@HBaseColumnAnnotation(name = "deviceFPID", family = "metadata")
	private String deviceFPID;

	@HBaseColumnAnnotation(name = "timeStamp", family = "metadata")
	private String timeStamp;
	@HBaseColumnAnnotation(name = "timeStampStr", family = "metadata")
	private String timeStampStr;
	@HBaseColumnAnnotation(name = "type", family = "metadata")
	private String type;
	@HBaseColumnAnnotation(name = "url", family = "metadata")
	private String url;
	@HBaseColumnAnnotation(name = "title", family = "metadata")
	private String title;

	
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTimeStampStr() {
		return timeStampStr;
	}

	public void setTimeStampStr(String timeStampStr) {
		this.timeStampStr = timeStampStr;
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
