package usertracker.browser.service.impl;

import java.util.List;

import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.VisitorLogService;

public class VisitorLogServiceImpl implements VisitorLogService {
	
	private VisitorLogDao visitorLogDao;
	
	public VisitorLogServiceImpl(VisitorLogDao visitorLogDao) {
		// TODO Auto-generated constructor stub
		this.visitorLogDao = visitorLogDao;
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, String column, String start, String last) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(clazz, column, start, last);
	}

	@Override
	public <T> T getOne(Class<T> clazz, String id) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getOne(clazz, id);
	}

	@Override
	public <T> List<T> find(Class<T> clazz, String word, String column) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.find(clazz, word, column);
	}

	@Override
	public void creatTable(Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		visitorLogDao.creatTable(clazz);
	}

	@Override
	public <T> T save(Class<T> clazz, Object object) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.save(clazz, object);
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, String column, String start, String last, String orderby)
			throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.getAll(clazz, column, start, last, orderby);
	}

	@Override
	public List<String> findColumnValues(Class<?> clazz, String columnReturn, String column, String word) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.findColumnValues(clazz, columnReturn, column, word);
	}

	@Override
	public List<WebEventModel> findWebEvents(String av, String start, String last, String orderby) throws Exception {
		// TODO Auto-generated method stub
		return visitorLogDao.findWebEvents(av, start, last, orderby);
	}

	
	
}
