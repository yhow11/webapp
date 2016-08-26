package usertracker.browser.service.impl;

import java.util.List;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.service.VisitorLogService;

public class VisitorLogServiceImpl implements VisitorLogService {
	
	private PhoenixDaoImpl phoenixDaoImpl;
	
	public VisitorLogServiceImpl(PhoenixDaoImpl phoenixDaoImpl) {
		// TODO Auto-generated constructor stub
		this.phoenixDaoImpl = phoenixDaoImpl;
	}

	@Override
	public VisitorLogModel save(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		return phoenixDaoImpl.upsert(model);
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		phoenixDaoImpl.delete(get(id));
	}

	@Override
	public VisitorLogModel get(Long id) throws Exception {
		// TODO Auto-generated method stub
		VisitorLogModel paramModel = new VisitorLogModel();
		paramModel.setId(id);
		List<VisitorLogModel> visitorLog = phoenixDaoImpl.search(VisitorLogModel.class, paramModel);
		return visitorLog.get(0);
	}

	@Override
	public List<VisitorLogModel> getAll(Long start, Long end) throws Exception {
		// TODO Auto-generated method stub
		VisitorLogModel paramModel = new VisitorLogModel();
		paramModel.setOffset(start);
		paramModel.setLimit(end);
		List<VisitorLogModel> visitorLog = phoenixDaoImpl.search(VisitorLogModel.class, paramModel);
		return visitorLog;
	}

	@Override
	public List<VisitorLogModel> getAll(Long start, Long end, boolean desc) throws Exception {
		// TODO Auto-generated method stub
		VisitorLogModel paramModel = new VisitorLogModel();
		paramModel.setOffset(start);
		paramModel.setLimit(end);
		List<VisitorLogModel> visitorLog = phoenixDaoImpl.search(VisitorLogModel.class, paramModel);
		return visitorLog;
	}

	
	
}
