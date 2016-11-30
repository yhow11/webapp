package sparkapp.collation.receiver.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import common.Loggable;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import sparkapp.collation.receiver.service.LogProcessor;
import usertracker.browser.browserfp.model.BrowserFPModel;
import usertracker.browser.devicefp.model.DeviceFPModel;
import usertracker.browser.session.model.SessionModel;
import usertracker.browser.visitor.AnonymousVisitorKeeper;
import usertracker.browser.visitor.model.VisitorModel;
import usertracker.browser.visitorlog.model.VisitorLogModel;
import usertracker.browser.webevent.mapper.VisitorLogModelToWebEventModelMapper;
import usertracker.browser.webevent.model.WebEventModel;

@Service("LogProcessorImpl")
@Transactional
public class LogProcessorImpl implements LogProcessor {

	
	@Resource(name="${LogProcessor.keeper}")
	private AnonymousVisitorKeeper keeper;
	@Resource(name="${LogProcessor.webEventStorage}")
	private Storage<WebEventModel> webEventStorage;
	@Resource(name="${LogProcessor.visitorStorage}")
	private Storage<VisitorLogModel> visitorStorage;

	@Loggable
	@Override
	public List<WebEventModel> process(List<VisitorLogModel> param, LogMetaData lmd) {
		// TODO Auto-generated method stub
		List<WebEventModel> result = new ArrayList<>();
		try{
			visitorStorage.save(param);
			
			for(VisitorLogModel visitorLogModel : param){
				WebEventModel webEvent = new VisitorLogModelToWebEventModelMapper(visitorLogModel.getVisitorID()).apply(visitorLogModel);
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
