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

import com.google.common.base.Strings;

import common.orm.query.param.Param;
import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.annotation.query.PhoenixDistinctColumn;

public class PhoenixUtil {
	public static String getTableName(Param<?> param) throws Exception {
		Class<?> clazz = param.getParamClass();
		return clazz.getAnnotation(PhoenixTable.class).table();
	}
	public static String getTableName(Class<?> clazz) throws Exception {
		return clazz.getAnnotation(PhoenixTable.class).table();
	}
	protected static String getSequenceName(Class<?> clazz, Field field) throws Exception {
		if(!Strings.isNullOrEmpty(field.getAnnotation(PhoenixSequence.class).name())) {
			return field.getAnnotation(PhoenixSequence.class).name();
		} else {
			return clazz.getAnnotation(PhoenixTable.class).table()+"_seq."+field.getName();
		}
	}
	protected static List<String> getColumnsValue(Object object) throws Exception {
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

	protected static Map<String, Type> getColumnsWithType(Class<?> clazz, String... columns) throws Exception {
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
	
	protected static String getFieldAliasesString(Class<?> clazz) throws Exception {
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
	protected static List<String> findFieldNames(Class<?> classs, Class<? extends Annotation> ann,
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
	protected static List<Field> findFields(Class<?> classs, Class<? extends Annotation> ann,
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

	protected static List<String> getConditions(Object object, List<String> fieldNames) throws Exception {

		List<String> conditions = new ArrayList<>();
		for (String name : fieldNames) {
			Object value = PropertyUtils.getProperty(object, name);
			if (value != null) {
				if (value.getClass().equals(String.class)) {
					if(value != ""){
						String orTemplate = "(%s OR %s)";
						String likeClause = name + " like " + getFieldValue(object, name);
						String equalsClause = name + " = " + getFieldValue(object, name);
						conditions.add(String.format(orTemplate, likeClause, equalsClause));
					}
				} else {
					conditions.add(name + " = " + getFieldValue(object, name));
				}

			}
		}

		return conditions;
	}

	protected static String getPaginationConditions(Long limit, Long offset) throws Exception {
		List<String> conditions = new ArrayList<>();
		if (limit != null) {
			conditions.add("limit " + limit);
		}
		if (offset != null) {
			conditions.add("offset " + offset);
		}
		if (conditions.size() > 0) {
			return String.join(" ", conditions);
		} else {
			return "";
		}
		
	}
	protected static String getGroupByCondition(List<String> columns) throws Exception {
		return columns.size() > 0 ? (" GROUP BY "+ String.join(",", columns)):"";
	}
	protected static String getOrderByCondition(Param<?> param) throws Exception {
		if(!Strings.isNullOrEmpty(param.getSortBy())) {
			return " ORDER BY "+param.getSortBy()+ " "+param.getSort().getType();
		} else {
			return "";
		}
		
	}
	
	protected static String getFieldValue(Object object, Field field) throws Exception {
		Object value = PropertyUtils.getProperty(object, field.getName());
		if (field.getType().equals(String.class)) {
			return "'" + (value != null ? String.valueOf(value) : "") + "'";
		} else {
			return value != null ? String.valueOf(value) : "";
		}
	}

	protected static Field getField(Class<?> clazz, String fieldName) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		for(Field field: fields){
			if(fieldName.equals(field.getName())){
				return field;
			}
		}
		
		return null;
	}
	protected static String getFieldValue(Object object, String fieldName) throws Exception {
		Object value = PropertyUtils.getProperty(object, fieldName);
		if (PropertyUtils.getPropertyType(object, fieldName).equals(String.class)) {
			return "'" + (value != null ? String.valueOf(value) : "") + "'";
		} else {
			return value != null ? String.valueOf(value) : "";
		}
	}
	protected static List<String> getFieldValue(Object object, List<String> fieldNames) throws Exception {
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
	
	
	public static <E, T> String createGetSQL(Param<T> param) throws Exception {
		String format = "SELECT %s FROM %s %s %s %s %s %s";
		Class<?> clazz = param.getParamClass();

		List<String> distinctFieldNames = findFieldNames(clazz, PhoenixDistinctColumn.class);
		List<String> fieldNames = findFieldNames(clazz, PhoenixColumn.class);
		List<String> params = new ArrayList<String>();
		params.add(String.join(",", distinctFieldNames.size() > 0? distinctFieldNames: fieldNames).toUpperCase());
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		List<String> conditions = getConditions(param.getModel(), fieldNames);
		conditions.addAll(param.getConditions());
		if(conditions.size() > 0) {
			params.add("WHERE");
		} else {
			params.add("");
		}
		params.add(String.join(" AND ", conditions));
		params.add(getGroupByCondition(distinctFieldNames));
		params.add(getOrderByCondition(param));
		params.add(getPaginationConditions(param.getLimit(), param.getOffset()));
		String sql = String.format(format, params.toArray(new Object[params.size()]));

		return sql;
	}
	public static String createCountSQL(Param<?> param) throws Exception {
		String format = "select COUNT(*) from (SELECT %s FROM %s %s %s  %s  %s)";
		Class<?> clazz = param.getParamClass();

		List<String> distinctFieldNames = findFieldNames(clazz, PhoenixDistinctColumn.class);
		List<String> allFieldNames = findFieldNames(clazz, PhoenixColumn.class); 
		List<String> params = new ArrayList<String>();
		params.add(String.join(",", distinctFieldNames.size() > 0? distinctFieldNames: allFieldNames));
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		List<String> conditions = getConditions(param.getModel(), allFieldNames);
		if(conditions.size() > 0) {
			params.add("WHERE");
		} else {
			params.add("");
		}
		params.add(String.join(" AND ", conditions));
		params.add(getGroupByCondition(distinctFieldNames));
		params.add(getPaginationConditions(param.getLimit(), param.getOffset()));
		String sql = String.format(format, params.toArray(new Object[params.size()]));

		return sql;
	}
}
