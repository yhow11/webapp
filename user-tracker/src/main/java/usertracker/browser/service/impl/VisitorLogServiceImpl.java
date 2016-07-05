package usertracker.browser.service.impl;

import java.util.List;

import hbase.annotation.HBaseTableAnnotation;
import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.service.VisitorLogService;

public class VisitorLogServiceImpl implements VisitorLogService {
	
	private VisitorLogDao visitorLogDao;
	
	public VisitorLogServiceImpl(VisitorLogDao visitorLogDao) {
		// TODO Auto-generated constructor stub
		this.visitorLogDao = visitorLogDao;
	}

	@Override
	public <T> List<T>  getAll(Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(clazz, null, null, null);
	}

	@Override
	public <T> List<T>  find(Class<T> clazz, String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.find(clazz, word, column);
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, Integer limit, String startRow, String lastRow)
			throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(clazz, limit, startRow, lastRow);
	}

	@Override
	public <T> List<T>  getAll(Class<T> clazz, Integer limit) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(clazz, limit, null, null);
	}

	@Override
	public <T> T creatTable(Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		visitorLogDao.creatTable(clazz.getAnnotation(HBaseTableAnnotation.class).tablename());
		
		return null;
	}

	@Override
	public <T> T save(Class<T> clazz, Object object) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.save(clazz, object);
	}

	@Override
	public <T> T getOne(Class<T> clazz, String id) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getOne(clazz, id);
	}

	
	
}
