package usertracker.browser.visitorlog.mapper;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("StringToVisitorLogModelMapper")
public class StringToVisitorLogModelMapper implements org.apache.spark.api.java.function.Function<String, VisitorLogModel>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(StringToVisitorLogModelMapper.class);

	@Override
	public VisitorLogModel call(String line) throws Exception {
		// TODO Auto-generated method stub
		
		VisitorLogModel log = new VisitorLogModel();
		try{
			if(line != "") {
				String[] raw = line.split("\\|\\|", -1);
				log.setLeadID(raw[0]);
//				event.setFp(raw[1]+"|"+raw[2]);
				log.setDeviceFP(raw[2]);
				log.setWebFP(raw[1]);
				log.setType(raw[4]);
				log.setUrl(raw[5]);
				log.setTimeStamp(Long.valueOf(raw[3]));
//		    	event.setTimeStampStr(raw[3]);
				log.setTitle(raw[6]);
				log.setSessionID(raw[7]);
				log.setElapsedTime(raw[8]);
				log.setCountry(raw[9]);
				log.setVisitorID(raw[10]);
			}
		}catch(Exception e){
			logger.error("Failed to parse "+line);
		}
		
		return log;
	}


}
