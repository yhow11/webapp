package com.fingerprint.visitor.manager;

import java.util.List;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.SessionService;

public class AnonymousVisitorManager {

	private AnonymousVisitorService anonymousVisitorService;
	private SessionService sessionService;
	private BrowserFPService browserFPService; 
	
	public AnonymousVisitorManager(AnonymousVisitorService anonymousVisitorService, SessionService sessionService, BrowserFPService browserFPService) {
		// TODO Auto-generated constructor stub
		this.anonymousVisitorService = anonymousVisitorService;
		this.sessionService = sessionService;
		this.browserFPService = browserFPService;
	}
	
	public AnonymousVisitorModel get(String sessionID, String browserFP) throws Exception{
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
			BrowserFPModel browserFPModel = browserFPService.get(browserFP);
			if (browserFPModel != null) {
				System.out.println("Finding Anonymous Visitor by Browser FP..");
				return anonymousVisitorService.get(browserFPModel.getAnonymousVisitorID());
			}
		}
		return null;
	}
}
