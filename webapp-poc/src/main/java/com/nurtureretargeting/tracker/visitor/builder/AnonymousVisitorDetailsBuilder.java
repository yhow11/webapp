package com.nurtureretargeting.tracker.visitor.builder;

import com.nurtureretargeting.tracker.visitor.manager.AnonymousVisitorManager;
import com.nurtureretargeting.tracker.visitor.object.AnonymousVisitorDetail;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.DeviceFPService;

public class AnonymousVisitorDetailsBuilder {

	private AnonymousVisitorManager anonymousVisitorManager;
	private BrowserFPService browserFPService;
	private DeviceFPService deviceFPService;

	public AnonymousVisitorDetailsBuilder(AnonymousVisitorManager anonymousVisitorManager,
			BrowserFPService browserFPService, DeviceFPService deviceFPService) {
		// TODO Auto-generated constructor stub
		this.anonymousVisitorManager = anonymousVisitorManager;
		this.browserFPService = browserFPService;
		this.deviceFPService = deviceFPService;
	}

	public AnonymousVisitorDetail build(String sessionID, String browserFP) throws Exception {
		AnonymousVisitorModel av = anonymousVisitorManager.get(sessionID, browserFP);

		AnonymousVisitorDetail details = new AnonymousVisitorDetail();
		details.setAnonymousUserID(av.getId());
		details.setBrowserFPs(browserFPService.getAll(av.getId()));
		details.setDeviceFPs(deviceFPService.getAll(av.getId()));
		return details;
	}
}
