package usertracker.browser.visitorlog.mapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.ObjectUtil;
import common.orm.mapper.Mapper;
import usertracker.browser.visitorlog.form.VisitorLogForm;
import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("VisitorLogMapper")
@Transactional
public class VisitorLogMapper extends Mapper<VisitorLogModel, VisitorLogForm> {

	public VisitorLogMapper() {
		super(VisitorLogModel.class, VisitorLogForm.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VisitorLogModel marshall(VisitorLogForm source, VisitorLogModel target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getDeviceFP(), value -> target.setDeviceFP(value));
		ObjectUtil.isPresent(source.getElapsedTime(), value -> target.setElapsedTime(value));
		ObjectUtil.isPresent(source.getId(), value -> target.setId(Long.valueOf(value)));
		ObjectUtil.isPresent(source.getLeadID(), value -> target.setLeadID(value));
		ObjectUtil.isPresent(source.getSessionID(), value -> target.setSessionID(value));
		ObjectUtil.isPresent(source.getTimeStamp(), value -> target.setTimeStamp(Long.valueOf(value)));
		ObjectUtil.isPresent(source.getTitle(), value -> target.setTitle(value));
		ObjectUtil.isPresent(source.getType(), value -> target.setType(value));
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		ObjectUtil.isPresent(source.getWebFP(), value -> target.setWebFP(value));
		ObjectUtil.isPresent(source.getCountry(), value -> target.setCountry(value));
		return target;
	}

	@Override
	public VisitorLogForm unmarshall(VisitorLogModel source, VisitorLogForm target) throws Exception {
		// TODO Auto-generated method stub
		ObjectUtil.isPresent(source.getDeviceFP(), value -> target.setDeviceFP(value));
		ObjectUtil.isPresent(source.getElapsedTime(), value -> target.setElapsedTime(value));
		ObjectUtil.isPresent(source.getId(), value -> target.setId(String.valueOf(value)));
		ObjectUtil.isPresent(source.getLeadID(), value -> target.setLeadID(value));
		ObjectUtil.isPresent(source.getSessionID(), value -> target.setSessionID(value));
		ObjectUtil.isPresent(source.getTimeStamp(), value -> target.setTimeStamp(String.valueOf(value)));
		ObjectUtil.isPresent(source.getTitle(), value -> target.setTitle(value));
		ObjectUtil.isPresent(source.getType(), value -> target.setType(value));
		ObjectUtil.isPresent(source.getUrl(), value -> target.setUrl(value));
		ObjectUtil.isPresent(source.getWebFP(), value -> target.setWebFP(value));
		ObjectUtil.isPresent(source.getCountry(), value -> target.setCountry(value));
		return target;
	}
	
	
}
