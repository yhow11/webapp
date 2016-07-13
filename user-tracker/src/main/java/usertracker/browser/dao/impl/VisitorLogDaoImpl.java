package usertracker.browser.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.jooq.impl.DefaultDSLContext;

import helper.phoenix.annotation.PhoenixTableAnnotation;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import helper.phoenix.util.PhoenixUtil;
import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.model.WebEventModel;


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
	@Override
	public List<WebEventModel> findWebEvents(String av, String start, String last, String orderby) throws Exception {
		// TODO Auto-generated method stub
		String queryStr = "select * from "+WebEventModel.class.getAnnotation(PhoenixTableAnnotation.class).table();
		queryStr += " where timestamp > :start and timestamp < :end and anonymousvisitorid =:av ";
		if(orderby != null){
			queryStr += " order by timestamp "+orderby;
		} 
		SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(queryStr);
		query.setParameter("start", Long.valueOf(start));
		query.setParameter("end", Long.valueOf(last));
		query.setParameter("av", av);
		query.setResultTransformer( Transformers.aliasToBean(WebEventModel.class));
		Map<String, Type> fields = PhoenixUtil.getFieldNamesWithType(WebEventModel.class);
		for(String key: fields.keySet()) {
			query.addScalar(key, fields.get(key));
		}
		return query.list();
	}
}
