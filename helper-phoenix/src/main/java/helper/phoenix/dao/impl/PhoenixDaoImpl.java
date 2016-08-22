package helper.phoenix.dao.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;

import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.util.PhoenixUtil;

public abstract class PhoenixDaoImpl extends HibernateTemplate {
	
	
	public PhoenixDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}
	
	public void delete(Object object){
		try {
			getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createDeleteSQL(object)).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <T> T upsert(Object entity) throws DataAccessException {
		// TODO Auto-generated method stub
		
		try {
			Field primaryField = PhoenixUtil.findFields(entity.getClass(), PhoenixID.class).get(0);
			Object value = PropertyUtils.getProperty(entity, primaryField.getName());
			if(value == null && primaryField.isAnnotationPresent(PhoenixSequence.class)) {
				SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createGetNextValueForSEQ(entity).get(0));
				Long id =  ((Number) query.uniqueResult()).longValue();
				PropertyUtils.setProperty(entity, primaryField.getName(), id);
			}
			getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createSaveSQL(entity)).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) entity;
	}
	
	public void createTable(Class<?> clazz, Class<?>... views) throws Exception {
		getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createDropTableSQL(clazz)).executeUpdate();
		getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createCreateTableSQL(clazz)).executeUpdate();
		createSequence(clazz);
	}
	
	public void createSequence(Class<?> clazz) throws Exception {
		for(String sql: PhoenixUtil.createDropSEQSQLs(clazz)){
			getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
		for(String sql: PhoenixUtil.createCreateSEQSQLs(clazz)){
			getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
	}
	
	public <T> List<T> search(Class<T> clazz, Object value) throws Exception {
		
		SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createGetSQL(value));
		query.setResultTransformer( Transformers.aliasToBean(clazz));
		Map<String, Type> fields = PhoenixUtil.getColumnsWithType(value.getClass());
		for(String key: fields.keySet()) {
			query.addScalar(key, fields.get(key));
		}
		return query.list();
	}
	

	public Long count(Object value) throws Exception {
		SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createCountSQL(value));
		return   ((Number) query.uniqueResult()).longValue();
	}
}
