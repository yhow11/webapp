package usertracker.browser.visitor;

import usertracker.browser.visitor.model.VisitorModel;

public interface AnonymousVisitorKeeper {
	public VisitorModel getOrCreateAV(String sessionID, String browserFP) throws Exception;
}
