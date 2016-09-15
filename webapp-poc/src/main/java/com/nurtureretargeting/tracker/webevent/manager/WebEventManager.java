package com.nurtureretargeting.tracker.webevent.manager;

import java.util.List;

import com.nurtureretargeting.tracker.visitor.manager.AnonymousVisitorManager;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.WebEventService;

public class WebEventManager {

	private WebEventService webEventService;
	private AnonymousVisitorManager anonymousVisitorManager;
	
	public WebEventManager(WebEventService webEventService, AnonymousVisitorManager anonymousVisitorManager) {
		// TODO Auto-generated constructor stub
		this.webEventService = webEventService;
		this.anonymousVisitorManager = anonymousVisitorManager;
	}
	
	public List<WebEventModel> getAll(String sessionID, String browserFP, String start, String end) throws Exception{
		AnonymousVisitorModel av = anonymousVisitorManager.get(sessionID, browserFP);
		if(av != null){
			return webEventService.getAll(av.getId(), Long.valueOf(start), Long.valueOf(end));
		}
		return null;
	}
}
