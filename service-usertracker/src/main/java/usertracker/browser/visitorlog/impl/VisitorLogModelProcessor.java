package usertracker.browser.visitorlog.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.spark.api.java.function.Function;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import usertracker.browser.browserfp.model.BrowserFPModel;
import usertracker.browser.devicefp.model.DeviceFPModel;
import usertracker.browser.session.model.SessionModel;
import usertracker.browser.visitor.AnonymousVisitorKeeper;
import usertracker.browser.visitor.model.VisitorModel;
import usertracker.browser.visitorlog.model.VisitorLogModel;
import usertracker.browser.webevent.mapper.VisitorLogModelToWebEventModelMapper;
import usertracker.browser.webevent.model.WebEventModel;

@Service("VisitorLogModelProcessor")
@Transactional
public class VisitorLogModelProcessor implements Function<List<VisitorLogModel>, List<WebEventModel>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="${VisitorLogModelProcessor.keeper}")
	private AnonymousVisitorKeeper keeper;
	@Resource(name="${VisitorLogModelProcessor.sessionStorage}")
	private Storage<SessionModel> sessionStorage;
	@Resource(name="${VisitorLogModelProcessor.browserFPStorage}")
	private Storage<BrowserFPModel> browserFPStorage;
	@Resource(name="${VisitorLogModelProcessor.deviceFPStorage}")
	private Storage<DeviceFPModel> deviceFPStorage;
	@Resource(name="${VisitorLogModelProcessor.webEventStorage}")
	private Storage<WebEventModel> webEventStorage;
	
	@Override
	public List<WebEventModel> call(List<VisitorLogModel> items) throws Exception {
		// TODO Auto-generated method stub
		List<WebEventModel> result = new ArrayList<>();
		try{
			for(VisitorLogModel visitorLogModel : items){
				VisitorModel visitor = keeper.getOrCreateAV(visitorLogModel.getSessionID(), visitorLogModel.getWebFP());
				
				Param<SessionModel> sessionParam = new DefaultParam<>(SessionModel.class);
				sessionParam.getModel().setANONYMOUSVISITORID(visitor.getID());
				sessionParam.getModel().setID(visitorLogModel.getSessionID());
				sessionStorage.getOrCreate(sessionParam);
				
				Param<BrowserFPModel> browserFPParam = new DefaultParam<>(BrowserFPModel.class);
				browserFPParam.getModel().setANONYMOUSVISITORID(visitor.getID());
				browserFPParam.getModel().setID(visitorLogModel.getWebFP());
				browserFPStorage.getOrCreate(browserFPParam);
				
				Param<DeviceFPModel> deviceFPParam = new DefaultParam<>(DeviceFPModel.class);
				deviceFPParam.getModel().setANONYMOUSVISITORID(visitor.getID());
				deviceFPParam.getModel().setID(visitorLogModel.getWebFP());
				deviceFPStorage.getOrCreate(deviceFPParam);
				
				WebEventModel webEvent = new VisitorLogModelToWebEventModelMapper(visitor.getID()).apply(visitorLogModel);
				System.out.println(webEvent.getTIMESTAMP());
				webEventStorage.save(webEvent);
				result.add(webEvent);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
