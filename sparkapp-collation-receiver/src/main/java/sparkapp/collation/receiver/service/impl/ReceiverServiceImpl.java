package sparkapp.collation.receiver.service.impl;

import java.util.UUID;

import sparkapp.collation.receiver.service.ReceiverService;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.DeviceFPService;
import usertracker.browser.service.SessionService;
import usertracker.browser.service.VisitorLogService;
import usertracker.browser.service.WebEventService;

public class ReceiverServiceImpl implements ReceiverService {

	private VisitorLogService visitorLogService;
	private AnonymousVisitorService anonymousVisitorService; 
	private WebEventService webEventService;
	private BrowserFPService browserFPService; 
	private DeviceFPService deviceFPService;
	private SessionService sessionService;
	
	public ReceiverServiceImpl(VisitorLogService visitorLogService, AnonymousVisitorService anonymousVisitorService,
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
	public AnonymousVisitorModel getOrCreateAV(String sessionID, String browserFP) throws Exception {
		// TODO Auto-generated method stub
		AnonymousVisitorModel av = getAV(sessionID, browserFP);
		if (av == null) {
			av = new AnonymousVisitorModel();
			av.setId(UUID.randomUUID().toString());
			anonymousVisitorService.save(av);
			System.out.println("Created New Anonymous Visitor " + av.getId());
		}
		return av;
	}

	@Override
	public SessionModel getOrCreateSession(String id, String avID) throws Exception {
		// TODO Auto-generated method stub
		return sessionService.getOrCreate(id, avID);
	}

	@Override
	public VisitorLogModel save(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogService.save(model);
	}

	@Override
	public AnonymousVisitorModel save(AnonymousVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return anonymousVisitorService.save(model);
	}

	@Override
	public WebEventModel save(WebEventModel model) throws Exception {
		// TODO Auto-generated method stub
		return webEventService.save(model);
	}

	@Override
	public BrowserFPModel save(BrowserFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return browserFPService.save(model);
	}

	@Override
	public DeviceFPModel save(DeviceFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return deviceFPService.save(model);
	}

	@Override
	public BrowserFPModel getOrCreateBrowserFP(String id, String avID) throws Exception {
		// TODO Auto-generated method stub
		return browserFPService.getOrCreate(id, avID);
	}

	@Override
	public DeviceFPModel getOrCreateDeviceFP(String id, String avID) throws Exception {
		// TODO Auto-generated method stub
		return deviceFPService.getOrCreate(id, avID);
	}
}
