package usertracker.browser.webevent.mapper;

import java.util.function.Function;

import usertracker.browser.visitorlog.model.VisitorLogModel;
import usertracker.browser.webevent.model.WebEventModel;

public class VisitorLogModelToWebEventModelMapper implements Function<VisitorLogModel, WebEventModel> {

	private String anonymousVisitorID;

	public VisitorLogModelToWebEventModelMapper(String anonymousVisitorID) {
		// TODO Auto-generated constructor stub
		this.anonymousVisitorID = anonymousVisitorID;
	}

	@Override
	public WebEventModel apply(VisitorLogModel source) {
		// TODO Auto-generated method stub
		WebEventModel target = new WebEventModel();
		target.setANONYMOUSVISITORID(anonymousVisitorID);
		target.setBROWSERFPID(source.getWebFP());
		target.setDEVICEFPID(source.getDeviceFP());
		target.setTIMESTAMP(source.getTimeStamp());
		target.setTITLE(source.getTitle());
		target.setTYPE(source.getType());
		target.setURL(source.getUrl());
		target.setELAPSEDTIME(source.getElapsedTime());
		target.setCOUNTRY(source.getCountry());
		return target;
	}


}
