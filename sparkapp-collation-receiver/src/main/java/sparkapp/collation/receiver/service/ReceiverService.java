package sparkapp.collation.receiver.service;

import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

public interface ReceiverService {

	public AnonymousVisitorModel getAV(String sessionID, String browserFPID) throws Exception;
	public AnonymousVisitorModel getOrCreateAV(String sessionID, String browserFP) throws Exception;
	public SessionModel getOrCreateSession(String id, String avID) throws Exception;
	public BrowserFPModel getOrCreateBrowserFP(String id, String avID) throws Exception;
	public DeviceFPModel getOrCreateDeviceFP(String id, String avID) throws Exception;
	public VisitorLogModel save(VisitorLogModel model) throws Exception;
	public AnonymousVisitorModel save(AnonymousVisitorModel model) throws Exception;
	public WebEventModel save(WebEventModel model) throws Exception;
	public BrowserFPModel save(BrowserFPModel model) throws Exception;
	public DeviceFPModel save(DeviceFPModel model) throws Exception;
}
