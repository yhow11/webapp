package usertracker.browser.visitorlog.mapper;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("StringToVisitorLogModelMapper")
public class StringToVisitorLogModelMapper implements org.apache.spark.api.java.function.Function<String, VisitorLogModel>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public VisitorLogModel call(String line) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		VisitorLogModel log = new VisitorLogModel();
		if(line != "") {
			String[] raw = line.split("\\|\\|", -1);
			log.setLeadID(raw[0]);
//			event.setFp(raw[1]+"|"+raw[2]);
			log.setDeviceFP(raw[2]);
			log.setWebFP(raw[1]);
			log.setType(raw[4]);
			log.setUrl(raw[5]);
			log.setTimeStamp(Long.valueOf(raw[3]));
//	    	event.setTimeStampStr(raw[3]);
			log.setTitle(raw[6]);
			log.setSessionID(raw[7]);
			log.setElapsedTime(raw[8]);
			log.setCountry(raw[9]);
		}
		return log;
	}


}
