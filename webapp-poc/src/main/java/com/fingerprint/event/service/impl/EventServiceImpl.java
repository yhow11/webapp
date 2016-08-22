package com.fingerprint.event.service.impl;

import java.util.List;

import com.fingerprint.event.object.FingerPrintSectionForm;
import com.fingerprint.event.service.EventService;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.DeviceFPService;
import usertracker.browser.service.SessionService;
import usertracker.browser.service.VisitorLogService;
import usertracker.browser.service.WebEventService;

public class EventServiceImpl implements EventService {

	private VisitorLogService visitorLogService;
	private AnonymousVisitorService anonymousVisitorService; 
	private WebEventService webEventService;
	private BrowserFPService browserFPService; 
	private DeviceFPService deviceFPService;
	private SessionService sessionService;
	
	public EventServiceImpl(VisitorLogService visitorLogService, AnonymousVisitorService anonymousVisitorService,
			WebEventService webEventService, BrowserFPService browserFPService, DeviceFPService deviceFPService,
			SessionService sessionService) {
		this.visitorLogService = visitorLogService;
		this.anonymousVisitorService = anonymousVisitorService;
		this.webEventService = webEventService;
		this.browserFPService = browserFPService;
		this.deviceFPService = deviceFPService;
		this.sessionService = sessionService;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public AnonymousVisitorModel getAV(String sessionID, String browserFPID) throws Exception {
		// TODO Auto-generated method stub
		AnonymousVisitorModel av = null;
		SessionModel sessionModel = sessionService.get(sessionID);
		if (sessionModel != null) {
			System.out.println("Finding Anonymous Visitor by session..");
			av = anonymousVisitorService.get(sessionModel.getAnonymousVisitorID());
			if (av != null) {
				System.out.println("Anonymous Vistor Found. " + av.getId());
				return av;
			}
		}
		if (av == null) {
			BrowserFPModel browserFPModel = browserFPService.get(browserFPID);
			if (browserFPModel != null) {
				System.out.println("Finding Anonymous Visitor by Browser FP..");
				return anonymousVisitorService.get(browserFPModel.getAnonymousVisitorID());
			}
		}
		return null;
	}

	@Override
	public List<WebEventModel> getWebEvents(String sessionID, String browserFPID, String start, String end)
			throws Exception {
		// TODO Auto-generated method stub
		
		AnonymousVisitorModel av = getAV(sessionID, browserFPID);
		if(av != null){
			return webEventService.getAll(av.getId(), Long.valueOf(start), Long.valueOf(end));
		}
		return null;
	}

	@Override
	public List<VisitorLogModel> getVisitorLogs(String start, String end) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogService.getAll(Long.valueOf(start), Long.valueOf(end));
	}
	
	@Override
	public List<SessionModel> getSessions(String sessionID, String browserFP) throws Exception {
		// TODO Auto-generated method stub
		AnonymousVisitorModel av = getAV(sessionID, browserFP);
		return sessionService.getAll(av.getId());
	}

	@Override
	public FingerPrintSectionForm createFingerPrintSectionForm(String sessionID, String browserFP) throws Exception {
		// TODO Auto-generated method stub
		AnonymousVisitorModel av = getAV(sessionID, browserFP);
		
		FingerPrintSectionForm form = new FingerPrintSectionForm();
		form.setAnonymousUserID(av.getId());
		form.setBrowserFPs(browserFPService.getAll(av.getId()));
		form.setDeviceFPs(deviceFPService.getAll(av.getId()));
		return form;
	}
}
