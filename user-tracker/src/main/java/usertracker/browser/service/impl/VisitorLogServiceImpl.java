package usertracker.browser.service.impl;

import java.util.List;

import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.service.VisitorLogService;

public class VisitorLogServiceImpl implements VisitorLogService {
	
	private VisitorLogDao visitorLogDao;
	
	public VisitorLogServiceImpl(VisitorLogDao visitorLogDao) {
		// TODO Auto-generated constructor stub
		this.visitorLogDao = visitorLogDao;
	}

	@Override
	public List<VisitorLogModel> getAll() throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(VisitorLogModel.class, null, null, null);
	}

	@Override
	public List<VisitorLogModel> find(String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.find(word, column);
	}

	@Override
	public List<VisitorLogModel> getAll(Integer limit, String startRow, String lastRow)
			throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(VisitorLogModel.class, limit, startRow, lastRow);
	}

	@Override
	public List<VisitorLogModel> getAll(Integer limit) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(VisitorLogModel.class, limit, null, null);
	}

	
	
}
