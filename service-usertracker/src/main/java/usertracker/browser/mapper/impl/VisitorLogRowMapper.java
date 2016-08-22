package usertracker.browser.mapper.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.spark.sql.Row;

import hbase.annotation.HBaseColumnAnnotation;
import usertracker.browser.mapper.VisitorLogMapper;
import usertracker.browser.model.VisitorLogModel;

public class VisitorLogRowMapper implements VisitorLogMapper<Row, VisitorLogModel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public VisitorLogModel map(Row t) throws Exception {
		// TODO Auto-generated method stub
		VisitorLogModel event = new VisitorLogModel();
		Field[] fields = VisitorLogModel.class.getDeclaredFields();
		for(Field field: fields){
			if(field.isAnnotationPresent(HBaseColumnAnnotation.class)){
				String name = field.getAnnotation(HBaseColumnAnnotation.class).name();
				try{
					PropertyUtils.setProperty(event, name, t.getAs(name));
				} catch (Exception e) {
					System.out.println("No value of"+name);
				}
				
				
			}
		}
    	return event;
	}

	@Override
	public Row unmap(VisitorLogModel e) {
		// TODO Auto-generated method stub
		return null;
	}

}
