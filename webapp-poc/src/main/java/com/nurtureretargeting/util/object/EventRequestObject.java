package com.nurtureretargeting.util.object;

public class EventRequestObject {

	private String sessionID;
	private String webFingerPrintID;
	private String deviceFingerPrintID;
	private String log;
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getWebFingerPrintID() {
		return webFingerPrintID;
	}
	public void setWebFingerPrintID(String webFingerPrintID) {
		this.webFingerPrintID = webFingerPrintID;
	}
	public String getDeviceFingerPrintID() {
		return deviceFingerPrintID;
	}
	public void setDeviceFingerPrintID(String deviceFingerPrintID) {
		this.deviceFingerPrintID = deviceFingerPrintID;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
	
}
