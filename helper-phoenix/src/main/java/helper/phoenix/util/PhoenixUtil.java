package helper.phoenix.util;

import static org.jooq.impl.DSL.fieldByName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.jooq.Record;

import helper.phoenix.annotation.PhoenixFieldAnnotation;
import helper.phoenix.annotation.PhoenixTableAnnotation;

public class PhoenixUtil {

	public static List<Object> getValues(Class<?> clazz, Object object) throws Exception{
		List<Object> valuesArr = new ArrayList<Object>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				Object value = PropertyUtils.getProperty(object, field.getName());
				valuesArr.add(value != null? value:" ");
			}
		}
	    return valuesArr;
	}
	public static List<org.jooq.Field<Object>> getFieldAliases(Class<?> clazz, Object object) throws Exception{
		List<org.jooq.Field<Object>> valuesArr = new ArrayList<org.jooq.Field<Object>>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				valuesArr.add(fieldByName(field.getName()));
			}
		}
	    return valuesArr;
	}
	public static List<String> getFieldNames(Class<?> clazz) throws Exception{
		List<String> valuesArr = new ArrayList<String>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				valuesArr.add(field.getName());
			}
		}
	    return valuesArr;
	}
	public static Map<String, Type> getFieldNamesWithType(Class<?> clazz) throws Exception{
		Map<String, Type> map = new HashMap<String, Type>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				if(field.getType().equals(Long.class)){
					map.put(field.getName(), StandardBasicTypes.LONG);
				} else {
					map.put(field.getName(), StandardBasicTypes.STRING);
				}
			}
		}
	    return map;
	}
	public static String getFieldAliasesString(Class<?> clazz) throws Exception{
		List<String> valuesArr = new ArrayList<String>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				valuesArr.add(field.getName()+" as "+field.getName());
			}
		}
	    return String.join(",", valuesArr);
	}
	public static String createSaveSQL(Class<?> clazz, Object object) throws Exception{
		String format = "upsert into "+clazz.getAnnotation(PhoenixTableAnnotation.class).table()+"  values (";
		List<Object> valuesArr = new ArrayList<Object>();
		List<String> dataTypesArr = new ArrayList<String>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				if (field.getType().equals(String.class)) {
			    	dataTypesArr.add("'%s'");
			    } else {
			    	dataTypesArr.add("%d");
			    }
				Object value = PropertyUtils.getProperty(object, field.getName());
				valuesArr.add(value != null? value:"");
			}
		}
		String joined = String.join(",", dataTypesArr);
		format += joined;
		format += ")";
	    String userSql = String.format(format,valuesArr.toArray(new Object[valuesArr.size()]));
	    return userSql;
	}
	
	public static String createCreateTableSQL(Class<?> clazz) throws Exception{
		String format = "CREATE TABLE "+clazz.getAnnotation(PhoenixTableAnnotation.class).table()+" (";
		List<String> columnStructArr = new ArrayList<String>();
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
				String joined = String.join(" ", field.getName(), field.getAnnotation(PhoenixFieldAnnotation.class).type()
						, field.getAnnotation(PhoenixFieldAnnotation.class).nullable() ? "":"NOT NULL"
							, field.getAnnotation(PhoenixFieldAnnotation.class).primary() ? "PRIMARY KEY":"");
				columnStructArr.add(joined);
			}
		}
		String joined = String.join(",", columnStructArr);
		format += joined;
		format += ")";
	    return format;
	}
	public static String createDeleteTableSQL(Class<?> clazz) throws Exception{
		String format = "DROP TABLE IF EXISTS "+clazz.getAnnotation(PhoenixTableAnnotation.class).table();
	    return format;
	}
	
	public static String findPrimaryKey(Class<?> clazz) throws Exception{
		for(Field field: clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(PhoenixFieldAnnotation.class) && field.getAnnotation(PhoenixFieldAnnotation.class).primary() ){
				return field.getName();
			}
		}
		return null;
	}
	public static <T> T map(Class<T> clazz, Record record) {
		Object newInstance = null;
		try {
			newInstance = clazz.newInstance();
			for(Field field: clazz.getDeclaredFields()) {
				if(field.isAnnotationPresent(PhoenixFieldAnnotation.class)){
					PropertyUtils.setProperty(newInstance, field.getName(), record.getValue(field.getName(), field.getType()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return (T) newInstance;
	}
}
