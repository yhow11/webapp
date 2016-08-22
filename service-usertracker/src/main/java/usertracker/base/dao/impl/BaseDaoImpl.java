package usertracker.base.dao.impl;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;

import hbase.annotation.HBaseColumnAnnotation;
import hbase.annotation.HBaseTableAnnotation;
import hbase.dao.HBaseDao;

public class BaseDaoImpl {

protected HBaseDao hbaseDao;
	
	public BaseDaoImpl(HBaseDao hbaseDao) {
		// TODO Auto-generated constructor stub
		this.hbaseDao = hbaseDao;
	}
	
	public void createTable(String table, String[] familys) throws Exception {
		hbaseDao.creatTable(table, familys);
	}
	
	public <T> T save(Object object, Class<T> t) throws Exception {
		
		
		System.out.println(t);
		System.out.println(t.getAnnotation(HBaseTableAnnotation.class).tablename());
		String tableName = t.getAnnotation(HBaseTableAnnotation.class).tablename();
		Field[] fields = t.getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			if (field.isAnnotationPresent(HBaseColumnAnnotation.class)) {
				String family = field.getAnnotation(HBaseColumnAnnotation.class).family();
				String qualifier = field.getAnnotation(HBaseColumnAnnotation.class).name();
				System.out.println(field.getName());
				hbaseDao.addRecord(tableName, String.valueOf(PropertyUtils.getProperty(object, "id")), family, qualifier,
						String.valueOf(PropertyUtils.getProperty(object, field.getName())));
			}
		}
		return (T) object;
	}
}
