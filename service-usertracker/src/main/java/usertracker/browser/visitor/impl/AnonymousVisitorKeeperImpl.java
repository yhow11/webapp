package usertracker.browser.visitor.impl;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import common.Loggable;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import usertracker.browser.browserfp.model.BrowserFPModel;
import usertracker.browser.devicefp.model.DeviceFPModel;
import usertracker.browser.session.model.SessionModel;
import usertracker.browser.visitor.AnonymousVisitorKeeper;
import usertracker.browser.visitor.model.VisitorModel;

@Service("AnonymousVisitorKeeperImpl")
@Transactional
public class AnonymousVisitorKeeperImpl implements AnonymousVisitorKeeper {

	
	@Resource(name="${AnonymousVisitorKeeperImpl.storage}")
	private Storage<VisitorModel> storage;
	@Resource(name="${AnonymousVisitorKeeperImpl.sessionStorage}")
	private Storage<SessionModel> sessionStorage;
	@Resource(name="${AnonymousVisitorKeeperImpl.browserFPStorage}")
	private Storage<BrowserFPModel> browserFPStorage;
	@Resource(name="${AnonymousVisitorKeeperImpl.deviceFPStorage}")
	private Storage<DeviceFPModel> deviceFPStorage;
	
	public AnonymousVisitorKeeperImpl() {
		// TODO Auto-generated constructor stub
	
	}
	
	private VisitorModel get(String sessionID, String browserFP) throws Exception{
		VisitorModel av = getBySession(sessionID);
		if (av == null) {
			av = getByBrowserFP(browserFP);
		}
		return av;
	}
	
	private VisitorModel getBySession(String sessionID) throws Exception{
		VisitorModel av = null;
		Param<SessionModel> sessionParam = new DefaultParam<>(SessionModel.class);
		sessionParam.getModel().setID(sessionID);
		Optional<SessionModel> session = sessionStorage.get(sessionParam).stream().findFirst();
		
		if(session.isPresent()){
			Param<VisitorModel> visitorParam = new DefaultParam<>(VisitorModel.class);
			sessionParam.getModel().setID(session.get().getANONYMOUSVISITORID());
			av = storage.get(visitorParam).stream().findFirst().get();
		}
		
		return av;
	}
	private VisitorModel getByBrowserFP(String browserFP) throws Exception{
		VisitorModel av = null;
		Param<BrowserFPModel> param = new DefaultParam<>(BrowserFPModel.class);
		param.getModel().setID(browserFP);
		Optional<BrowserFPModel> model = browserFPStorage.get(param).stream().findFirst();
		
		if(model.isPresent()){
			Param<VisitorModel> visitorParam = new DefaultParam<>(VisitorModel.class);
			visitorParam.getModel().setID(model.get().getANONYMOUSVISITORID());
			Optional<VisitorModel> visitor = storage.get(visitorParam).stream().findFirst();
			if(visitor.isPresent())
				av = visitor.get();
		}
		
		return av;
	}
	@Loggable
	@Override
	public VisitorModel getOrCreateAV(String sessionID, String browserFP, String deviceFP, LogMetaData lmd) throws Exception {
		// TODO Auto-generated method stub
		VisitorModel av = get(sessionID, browserFP);
		if (av == null) {
			av = new VisitorModel();
			av.setID(UUID.randomUUID().toString());
			storage.save(av);
			
			Param<SessionModel> sessionParam = new DefaultParam<>(SessionModel.class);
			sessionParam.getModel().setANONYMOUSVISITORID(av.getID());
			sessionParam.getModel().setID(sessionID);
			sessionStorage.getOrCreate(sessionParam, lmd);
			
			Param<BrowserFPModel> browserFPParam = new DefaultParam<>(BrowserFPModel.class);
			browserFPParam.getModel().setANONYMOUSVISITORID(av.getID());
			browserFPParam.getModel().setID(deviceFP);
			browserFPStorage.getOrCreate(browserFPParam, lmd);
			
			Param<DeviceFPModel> deviceFPParam = new DefaultParam<>(DeviceFPModel.class);
			deviceFPParam.getModel().setANONYMOUSVISITORID(av.getID());
			deviceFPParam.getModel().setID(browserFP);
			deviceFPStorage.getOrCreate(deviceFPParam, lmd);
			
			lmd.getTransactions().add("Created New Visitor: "+av.getID());
		} else {
			lmd.getTransactions().add("Found Visitor: "+av.getID());
		}
		return av;
	}

	@Override
	public VisitorModel get(String sessionID, String browserFP, LogMetaData lmd) throws Exception {
		// TODO Auto-generated method stub
		return get(sessionID, browserFP);
	}
	

}
