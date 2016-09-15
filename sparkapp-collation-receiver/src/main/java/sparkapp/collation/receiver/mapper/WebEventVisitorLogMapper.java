package sparkapp.collation.receiver.mapper;

import common.mapper.Mapper;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;

public class WebEventVisitorLogMapper implements Mapper<WebEventModel, VisitorLogModel> {

	private String anonymousVisitorID;
	
	public WebEventVisitorLogMapper(String anonymousVisitorID) {
		// TODO Auto-generated constructor stub
		this.anonymousVisitorID = anonymousVisitorID;
	}

	@Override
	public WebEventModel marshall(VisitorLogModel param) throws Exception {
		// TODO Auto-generated method stub
		return marshall(param, new WebEventModel());
	}

	@Override
	public VisitorLogModel unmarshall(WebEventModel param) throws Exception {
		// TODO Auto-generated method stub
		return unmarshall(param, new VisitorLogModel());
	}

	@Override
	public WebEventModel marshall(VisitorLogModel e, WebEventModel target) throws Exception {
		// TODO Auto-generated method stub
		target.setAnonymousVisitorID(anonymousVisitorID);
		target.setBrowserFPID(e.getWebFP());
		target.setDeviceFPID(e.getDeviceFP());
		target.setTimeStamp(e.getTimeStamp());
		target.setTitle(e.getTitle());
		target.setType(e.getType());
		target.setUrl(e.getUrl());
		return target;
	}

	@Override
	public VisitorLogModel unmarshall(WebEventModel t, VisitorLogModel target) throws Exception {
		// TODO Auto-generated method stub
		target.setDeviceFP(t.getDeviceFPID());
		target.setWebFP(t.getBrowserFPID());
		target.setTimeStamp(t.getTimeStamp());
		target.setTitle(t.getTitle());
		target.setType(t.getType());
		target.setUrl(t.getUrl());
		return target;
	}
}
