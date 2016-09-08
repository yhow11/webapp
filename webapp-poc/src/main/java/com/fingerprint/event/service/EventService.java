package com.fingerprint.event.service;

import java.util.List;

import com.fingerprint.event.object.FingerPrintSectionForm;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

public interface EventService {

	public AnonymousVisitorModel getAV(String sessionID, String browserFP) throws Exception;
	public List<WebEventModel> getWebEvents(String sessionID, String browserFP, String start, String end) throws Exception;
	public List<SessionModel> getSessions(String sessionID, String browserFP) throws Exception;
	public FingerPrintSectionForm createFingerPrintSectionForm(String sessionID, String browserFP) throws Exception;
}
