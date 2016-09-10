package com.nurtureretargeting.tracker.session.manager;

import java.util.List;

import com.nurtureretargeting.tracker.visitor.manager.AnonymousVisitorManager;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.service.SessionService;

public class SessionManager {

	private AnonymousVisitorManager anonymousVisitorManager;
	private SessionService sessionService;
	
	public SessionManager(AnonymousVisitorManager anonymousVisitorManager, SessionService sessionService) {
		// TODO Auto-generated constructor stub
		this.anonymousVisitorManager = anonymousVisitorManager;
		this.sessionService = sessionService;
	}
	
	public List<SessionModel> getAll(String sessionID, String browserFP) throws Exception{
		AnonymousVisitorModel avModel = anonymousVisitorManager.get(sessionID, browserFP);
		return sessionService.getAll(avModel.getId());
	}
}
