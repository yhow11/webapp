package usertracker.browser.visitor;

import common.LogMetaData;
import usertracker.browser.visitor.model.VisitorModel;

public interface AnonymousVisitorKeeper {
	public VisitorModel getOrCreateAV(String sessionID, String browserFP, String deviceFP, LogMetaData lmd) throws Exception;
	public VisitorModel get(String sessionID, String browserFP, LogMetaData lmd) throws Exception;
}
