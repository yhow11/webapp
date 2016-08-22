package helper.phoenix.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.core.annotation.AnnotationUtils;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.annotation.query.PhoenixDistinctColumn;
import helper.phoenix.annotation.query.PhoenixPaginated;
import joptsimple.internal.Strings;

public class PhoenixUtil {

	private static String getSequenceName(Class<?> clazz, Field field) throws Exception {
		if(!Strings.isNullOrEmpty(field.getAnnotation(PhoenixSequence.class).name())) {
			return field.getAnnotation(PhoenixSequence.class).name();
		} else {
			return clazz.getAnnotation(PhoenixTable.class).table()+"_seq."+field.getName();
		}
	}
	private static List<String> getColumnsValue(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		List<Field> fields = findFields(clazz, PhoenixColumn.class);
		List<String> valuesArr = new ArrayList<String>();
		for (Field field : fields) {
			Object value = PropertyUtils.getProperty(object, field.getName());

			if (field.isAnnotationPresent(PhoenixSequence.class)) {
				if (value == null) {
					valuesArr.add("NEXT VALUE FOR " + getSequenceName(clazz, field));
				} else {
					valuesArr.add(getFieldValue(object, field));
				}
			} else {
				valuesArr.add(getFieldValue(object, field));
			}
		}
		return valuesArr;
	}

	public static Map<String, Type> getColumnsWithType(Class<?> clazz) throws Exception {
		Map<String, Type> map = new HashMap<String, Type>();
		List<Field> distinctFields = findFields(clazz, PhoenixDistinctColumn.class);
		List<Field> allFields = findFields(clazz, PhoenixColumn.class);
		List<Field> fields = distinctFields.size() > 0? distinctFields: allFields;
		for (Field field : fields) {
			if (field.getType().equals(Long.class)) {
				map.put(field.getName(), StandardBasicTypes.LONG);
			} else {
				map.put(field.getName(), StandardBasicTypes.STRING);
			}
		}
		return map;
	}

	public static Map<String, Type> getColumnsWithType(Class<?> clazz, String... columns) throws Exception {
		Map<String, Type> map = new HashMap<String, Type>();
		List<Field> fields = new ArrayList<>();
		for(String column: columns){
			fields.add(FieldUtils.getDeclaredField(clazz, column));
		}
		for (Field field : fields) {
			if (field.getType().equals(Long.class)) {
				map.put(field.getName(), StandardBasicTypes.LONG);
			} else {
				map.put(field.getName(), StandardBasicTypes.STRING);
			}
		}
		return map;
	}
	
	private static String getFieldAliasesString(Class<?> clazz) throws Exception {
		List<String> valuesArr = new ArrayList<String>();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(PhoenixColumn.class)) {
				valuesArr.add(field.getName() + " as " + field.getName());
			}
		}
		return String.join(",", valuesArr);
	}

	public static List<Field> findFields(Class<?> classs, Class<? extends Annotation> ann) {
		return findFields(classs, ann, null);
	}

	public static List<String> findFieldNames(Class<?> classs, Class<? extends Annotation> ann) {
		return findFieldNames(classs, ann, null);
	}
	private static List<String> findFieldNames(Class<?> classs, Class<? extends Annotation> ann,
			Class<? extends Annotation>... exclude) {
		List<String> fields = new ArrayList<>();
		Class<?> c = classs;
		while (c != null) {
			for (Field field : c.getDeclaredFields()) {
				boolean add = true;
				if (exclude != null) {
					for (Class<? extends Annotation> ex : exclude) {
						if (field.isAnnotationPresent(ex)) {
							add = false;
						}
					}
				}
				if (field.isAnnotationPresent(ann) && add) {
					fields.add(field.getName());
				}
			}
			c = c.getSuperclass();
		}
		return fields;
	}
	private static List<Field> findFields(Class<?> classs, Class<? extends Annotation> ann,
			Class<? extends Annotation>... exclude) {
		List<Field> fields = new ArrayList<>();
		Class<?> c = classs;
		while (c != null) {
			for (Field field : c.getDeclaredFields()) {
				boolean add = true;
				if (exclude != null) {
					for (Class<? extends Annotation> ex : exclude) {
						if (field.isAnnotationPresent(ex)) {
							add = false;
						}
					}
				}
				if (field.isAnnotationPresent(ann) && add) {
					fields.add(field);
				}
			}
			c = c.getSuperclass();
		}
		return fields;
	}

	private static List<String> getConditions(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		Class<?> superClazz = object.getClass().getSuperclass();
		List<String> conditions = new ArrayList<>();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(PhoenixColumn.class)) {
				Object value = PropertyUtils.getProperty(object, field.getName());
				if (value != null) {
					if (field.getType().equals(String.class)) {
						conditions.add(field.getName() + " like " + getFieldValue(object, field));
					} else {
						conditions.add(field.getName() + " = " + getFieldValue(object, field));
					}

				}
			}
		}

		return conditions;
	}

	private static String getPaginationConditions(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		List<String> conditions = new ArrayList<>();

		if (AnnotationUtils.findAnnotation(clazz, PhoenixPaginated.class) != null) {
			Long offset = (Long) PropertyUtils.getProperty(object,
					AnnotationUtils.findAnnotation(clazz, PhoenixPaginated.class).offsetField());
			Long limit = (Long) PropertyUtils.getProperty(object,
					AnnotationUtils.findAnnotation(clazz, PhoenixPaginated.class).limitField());
			if (limit != null) {
				conditions.add("limit " + limit);
			}
			if (offset != null) {
				conditions.add("offset " + offset);
			}
		}
		if (conditions.size() > 0) {
			return String.join(" ", conditions);
		} else {
			return "";
		}
		
	}
	private static String getGroupByCondition(List<String> columns) throws Exception {
		return columns.size() > 0 ? (" GROUP BY "+ String.join(",", columns)):"";
	}
	private static String getFieldValue(Object object, Field field) throws Exception {
		Object value = PropertyUtils.getProperty(object, field.getName());
		if (field.getType().equals(String.class)) {
			return "'" + (value != null ? String.valueOf(value) : "") + "'";
		} else {
			return value != null ? String.valueOf(value) : "";
		}
	}

	private static Field getField(Class<?> clazz, String fieldName) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields){
			if(fieldName.equals(field.getName())){
				return field;
			}
		}
		
		return null;
	}
	private static String getFieldValue(Object object, String fieldName) throws Exception {
		Object value = PropertyUtils.getProperty(object, fieldName);
		Field field = getField(object.getClass(), fieldName);
		if (field.getType().equals(String.class)) {
			return "'" + (value != null ? String.valueOf(value) : "") + "'";
		} else {
			return value != null ? String.valueOf(value) : "";
		}
	}
	private static List<String> getFieldValue(Object object, List<String> fieldNames) throws Exception {
		List<String> values = new ArrayList<>();
		for(String fieldName: fieldNames){
			values.add(getFieldValue(object, fieldName));
		}
		return values;
	}
	public static String createDeleteSQL(Object object) throws Exception {
		String format = "DELETE FROM %s  WHERE (%s) = (%s)";
		Class<?> clazz = object.getClass();

		String table = clazz.getAnnotation(PhoenixTable.class).table();
		List<String> primaryKeyFields = findFieldNames(clazz, PhoenixID.class);

		List<Object> valuesArr = new ArrayList<Object>();
		valuesArr.add(table);
		valuesArr.add(String.join(",", primaryKeyFields));
		valuesArr.add(String.join(",", getFieldValue(object, primaryKeyFields)));

		String userSql = String.format(format, valuesArr.toArray(new Object[valuesArr.size()]));
		return userSql;
	}

	public static String createSaveSQL(Object object) throws Exception {
		String format = "UPSERT INTO %s(%s)  values (%s)";
		Class<?> clazz = object.getClass();

		List<String> params = new ArrayList<String>();
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		params.add(String.join(",", findFieldNames(clazz, PhoenixColumn.class)));
		params.add(String.join(",", getColumnsValue(object)));

		String userSql = String.format(format, params.toArray(new Object[params.size()]));
		return userSql;
	}
	public static List<String> createGetNextValueForSEQ(Object object) throws Exception {
		String format = "SELECT NEXT VALUE FOR %s as id";
		Class<?> clazz = object.getClass();

		List<String> sqls = new ArrayList<String>();
		List<Field> fields = findFields(clazz, PhoenixSequence.class);
		for(Field field : fields) {
			List<String> params = new ArrayList<String>();
			params.add(getSequenceName(clazz, field));
			sqls.add(String.format(format, params.toArray(new Object[params.size()])));
		}
		
		return sqls;
	}
	public static String createCreateTableSQL(Class<?> clazz) throws Exception {
		String format = "CREATE TABLE %s(%s)";

		List<String> primaries = findFieldNames(clazz, PhoenixID.class);
		List<Field> columns = findFields(clazz, PhoenixColumn.class);

		List<String> columnStructArr = new ArrayList<String>();
		for (Field field : columns) {
			if (field.isAnnotationPresent(PhoenixColumn.class)) {
				columnStructArr.add(String.join(" ", field.getName(), field.getAnnotation(PhoenixColumn.class).type(),
						field.getAnnotation(PhoenixColumn.class).nullable() ? "" : "NOT NULL"));
			}
		}

		columnStructArr.add(" CONSTRAINT PK PRIMARY KEY ("+String.join(",", primaries)+")");
		List<String> params = new ArrayList<String>();
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		params.add(String.join(",", columnStructArr));

		String userSql = String.format(format, params.toArray(new Object[params.size()]));
		return userSql;
	}

	public static String createDropTableSQL(Class<?> clazz) throws Exception {
		String format = "DROP TABLE IF EXISTS " + clazz.getAnnotation(PhoenixTable.class).table();
		return format;
	}

	public static List<String> createCreateSEQSQLs(Class<?> clazz) throws Exception {
		String format = "CREATE SEQUENCE ";
		List<String> sqls = new ArrayList<String>();
		for (Field field : findFields(clazz, PhoenixSequence.class)) {
			sqls.add(format + getSequenceName(clazz, field));
		}
		return sqls;
	}

	public static List<String> createDropSEQSQLs(Class<?> clazz) throws Exception {
		String format = "DROP SEQUENCE IF EXISTS ";
		List<String> sqls = new ArrayList<String>();
		for (Field field : findFields(clazz, PhoenixSequence.class)) {
			sqls.add(format + getSequenceName(clazz, field));
		}
		return sqls;
	}

	public static String createGetSQL(Object value) throws Exception {
		String format = "SELECT %s FROM %s WHERE %s %s %s";
		Class<?> clazz = value.getClass();

		List<String> distinctFieldNames = findFieldNames(clazz, PhoenixDistinctColumn.class);
		List<String> fieldNames = findFieldNames(clazz, PhoenixColumn.class);
		List<String> params = new ArrayList<String>();
		params.add(String.join(",", distinctFieldNames.size() > 0? distinctFieldNames: fieldNames));
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		params.add(String.join(" AND ", getConditions(value)));
		params.add(getGroupByCondition(distinctFieldNames));
		params.add(getPaginationConditions(value));
		String sql = String.format(format, params.toArray(new Object[params.size()]));

		return sql;
	}
	public static String createCountSQL(Object value) throws Exception {
		String format = "select COUNT(*) from (SELECT %s FROM %s WHERE %s  %s)";
		Class<?> clazz = value.getClass();

		List<String> distinctFieldNames = findFieldNames(value.getClass(), PhoenixDistinctColumn.class);
		List<String> allFieldNames = findFieldNames(value.getClass(), PhoenixColumn.class); 
		List<String> params = new ArrayList<String>();
		params.add(String.join(",", distinctFieldNames.size() > 0? distinctFieldNames: allFieldNames));
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		params.add(String.join(" AND ", getConditions(value)));
		params.add(getGroupByCondition(distinctFieldNames));
		String sql = String.format(format, params.toArray(new Object[params.size()]));

		return sql;
	}
}
