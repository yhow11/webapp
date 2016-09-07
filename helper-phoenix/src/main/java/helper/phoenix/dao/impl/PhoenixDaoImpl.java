package helper.phoenix.dao.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import common.query.QueryParam;
import common.query.QueryResponse;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.util.PhoenixUtil;

@Transactional
public abstract class PhoenixDaoImpl {
	
	private SessionFactory sessionFactory;
	
	public PhoenixDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = sessionFactory;
	}
	
	public void delete(Object model){
		try {
			sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createDeleteSQL(model)).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <T> T upsert(Object model) throws DataAccessException {
		// TODO Auto-generated method stub
		
		try {
			Field primaryField = PhoenixUtil.findFields(model.getClass(), PhoenixID.class).get(0);
			Object value = PropertyUtils.getProperty(model, primaryField.getName());
			if(value == null && primaryField.isAnnotationPresent(PhoenixSequence.class)) {
				SQLQuery query  = sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createGetNextValueForSEQ(model).get(0));
				Long id =  ((Number) query.uniqueResult()).longValue();
				PropertyUtils.setProperty(model, primaryField.getName(), id);
			}
			sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createSaveSQL(model)).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) model;
	}
	
	public void createTable(Class<?> clazz, Class<?>... views) throws Exception {
		sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createDropTableSQL(clazz)).executeUpdate();
		sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createCreateTableSQL(clazz)).executeUpdate();
		createSequence(clazz);
	}
	
	public void createSequence(Class<?> clazz) throws Exception {
		for(String sql: PhoenixUtil.createDropSEQSQLs(clazz)){
			sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
		for(String sql: PhoenixUtil.createCreateSEQSQLs(clazz)){
			sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
	}
	
	public <T> List<T> search(QueryParam<T> param) throws Exception {
		Class<T> clazz = param.getModelClass();
		SQLQuery query  = sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createGetSQL(param));
		query.setResultTransformer( Transformers.aliasToBean(clazz));
		Map<String, Type> fields = PhoenixUtil.getColumnsWithType(clazz);
		for(String key: fields.keySet()) {
			query.addScalar(key, fields.get(key));
		}
		
		return query.list();
	}
	
	public <T> T searchOne(QueryParam<T> param) throws Exception {
		param.setLimit(1L);
		List<T> results = search(param);
		if(results.size() > 0){
			return results.get(0);
		}
		return null;
		
	}

	public Long count(QueryParam<?> param) throws Exception {
		SQLQuery query  = sessionFactory.getCurrentSession().createSQLQuery(PhoenixUtil.createCountSQL(param));
		return   ((Number) query.uniqueResult()).longValue();
	}
	
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
