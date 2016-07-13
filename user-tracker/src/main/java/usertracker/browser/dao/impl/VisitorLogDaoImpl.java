package usertracker.browser.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jooq.impl.DefaultDSLContext;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import usertracker.browser.dao.VisitorLogDao;


public class VisitorLogDaoImpl extends PhoenixDaoImpl implements VisitorLogDao {

	public VisitorLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		// TODO Auto-generated constructor stub
	}
	public VisitorLogDaoImpl(DefaultDSLContext defaultDSLContext, SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(defaultDSLContext, sessionFactory);
	}
	@Override
	public <T> List<T> find(Class<T> clazz, String column, String word) throws Exception {
		// TODO Auto-generated method stub
		return super.find(clazz, column, word);
	}

	@Override
	public void creatTable(Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		super.createTable(clazz);
	}

	@Override
	public <T> T save(Class<T> clazz, Object object) throws Exception {
		// TODO Auto-generated method stub
		super.save(clazz, object);
		return (T) object;
	}

	@Override
	public <T> T getOne(Class<T> clazz, String id) throws Exception {
		// TODO Auto-generated method stub
		return super.findById(clazz, id);
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, String column, String start, String last) throws Exception {
		// TODO Auto-generated method stub
		return super.getAll(clazz, column, start, last);
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz, String column, String start, String last, String orderby) throws Exception {
		// TODO Auto-generated method stub
		return super.getAll(clazz, column, start, last, orderby);
	}
	@Override
	public List<String> findColumnValues(Class<?> clazz, String columnReturn, String column, String word) throws Exception {
		// TODO Auto-generated method stub
		return super.findColumnValues(clazz, columnReturn, column, word);
	}
}
