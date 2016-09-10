package com.nurtureretargeting.event.object;

import java.util.ArrayList;
import java.util.List;

import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;

public class FingerPrintSectionForm {

	private String anonymousUserID = "";
	private List<BrowserFPModel> browserFPs = new ArrayList<BrowserFPModel>();
	private List<DeviceFPModel> deviceFPs = new ArrayList<DeviceFPModel>();
	
	
	
	public String getAnonymousUserID() {
		return anonymousUserID;
	}
	public void setAnonymousUserID(String anonymousUserID) {
		this.anonymousUserID = anonymousUserID;
	}
	public List<BrowserFPModel> getBrowserFPs() {
		return browserFPs;
	}
	public void setBrowserFPs(List<BrowserFPModel> browserFPs) {
		this.browserFPs = browserFPs;
	}
	public List<DeviceFPModel> getDeviceFPs() {
		return deviceFPs;
	}
	public void setDeviceFPs(List<DeviceFPModel> deviceFPs) {
		this.deviceFPs = deviceFPs;
	}
	
	
}
