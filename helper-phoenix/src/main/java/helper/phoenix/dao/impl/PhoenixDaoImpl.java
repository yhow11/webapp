package helper.phoenix.dao.impl;

import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.DSL.tableByName;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import helper.phoenix.annotation.PhoenixTableAnnotation;
import helper.phoenix.dao.PhoenixDao;
import helper.phoenix.util.PhoenixUtil;


public abstract class PhoenixDaoImpl extends HibernateTemplate implements PhoenixDao {
	
	private DefaultDSLContext defaultDSLContext;
	
	public PhoenixDaoImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
	}
	public PhoenixDaoImpl(DefaultDSLContext defaultDSLContext, SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		super(sessionFactory);
		this.defaultDSLContext = defaultDSLContext;
		
	}
	
	public <T> T save(Class<T> clazz, Object object) throws Exception {
		getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createSaveSQL(clazz, object)).executeUpdate();
//		defaultDSLContext.query().execute();
        return (T) object;
	}
	
	public void createTable(Class<?> clazz) throws Exception {
		getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createDeleteTableSQL(clazz)).executeUpdate();
		getSessionFactory().getCurrentSession().createSQLQuery(PhoenixUtil.createCreateTableSQL(clazz)).executeUpdate();
	}
	
	public <T> List<T> find(Class<T> clazz, String column, String value) throws Exception {
		return find(clazz,column,value, 0);
	}
	public List<String> findColumnValues(Class<?> clazz, String columnReturn, String column, String word) throws Exception {
		String queryStr = "select "+columnReturn+" from "+clazz.getAnnotation(PhoenixTableAnnotation.class).table();
		queryStr += " where "+column+" =:value ";
		SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(queryStr);
		query.setParameter("value", column);
		return (List<String>) query.list();
	}
	
	public <T> List<T> find(Class<T> clazz, String column, String value, int limit) throws Exception {
		String queryStr = "";
		if(limit > 0) {
			queryStr = defaultDSLContext
	        .select()
	        .from(tableByName(clazz.getAnnotation(PhoenixTableAnnotation.class).table()))
	        .where(fieldByName(column).eq(value)).limit(limit).toString().replace("`", "").replace("offset 0", "");
		} else {
			queryStr = defaultDSLContext
	        .select()
	        .from(tableByName(clazz.getAnnotation(PhoenixTableAnnotation.class).table()))
	        .where(fieldByName(column).eq(value)).toString().replace("`", "");
		}
		
        
		SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(queryStr);
		query.setResultTransformer( Transformers.aliasToBean(clazz));
		Map<String, Type> fields = PhoenixUtil.getFieldNamesWithType(clazz);
		for(String key: fields.keySet()) {
			query.addScalar(key, fields.get(key));
		}
		return query.list();
	}
	public <T> T findById(Class<T> clazz,String value) throws Exception {
		List<T> result = find(clazz, PhoenixUtil.findPrimaryKey(clazz),value, 1);
		if(result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public <T> List<T> getAll(Class<T> clazz, String column,  String start, String last) throws Exception {
		return getAll(clazz, column,  start, last, null);
	}
	public <T> List<T> getAll(Class<T> clazz, String column,  String start, String last, String orderby) throws Exception {
		String queryStr = "select * from "+clazz.getAnnotation(PhoenixTableAnnotation.class).table();
		queryStr += " where "+column+" > :start and "+column+" < :end ";
		if(orderby != null){
			queryStr += " order by "+column+" "+orderby;
		} 
		SQLQuery query  = getSessionFactory().getCurrentSession().createSQLQuery(queryStr);
		query.setParameter("start", Long.valueOf(start));
		query.setParameter("end", Long.valueOf(last));
		query.setResultTransformer( Transformers.aliasToBean(clazz));
		Map<String, Type> fields = PhoenixUtil.getFieldNamesWithType(clazz);
		for(String key: fields.keySet()) {
			query.addScalar(key, fields.get(key));
		}
		return query.list();
	}
}
