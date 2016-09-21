package usertracker.browser.mapper.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import usertracker.browser.mapper.VisitorLogMapper;
import usertracker.browser.model.VisitorLogModel;

public class VisitorLogStringMapper implements VisitorLogMapper<String, VisitorLogModel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public VisitorLogModel map(String t) throws Exception {
		// TODO Auto-generated method stub
		VisitorLogModel event = new VisitorLogModel();
		if(t != "") {
			String[] raw = t.split("\\|", -1);
			event.setLeadID(raw[0]);
//			event.setFp(raw[1]+"|"+raw[2]);
	    	event.setDeviceFP(raw[2]);
	    	event.setWebFP(raw[1]);
	    	event.setType(raw[4]);
	    	event.setUrl(raw[5]);
	    	event.setTimeStamp(Long.valueOf(raw[3]));
//	    	event.setTimeStampStr(raw[3]);
	    	event.setTitle(raw[6]);
	    	event.setSessionID(raw[7]);
	    	event.setElapsedTime(raw[8]);
		}
		
    	return event;
	}

	@Override
	public String unmap(VisitorLogModel e) {
		// TODO Auto-generated method stub
		return null;
	}

}
