package helper.spark.sql.util;

import java.util.ArrayList;
import java.util.List;

import common.query.QueryParam;
import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.annotation.query.PhoenixDistinctColumn;
import helper.phoenix.util.PhoenixUtil;

public class SparkSQLUtil extends PhoenixUtil {

	public static <E, T> String createGetSQL(QueryParam<T> param) throws Exception {
		String format = "SELECT row_number() over (order by ID) as pagenumber, %s FROM %s %s %s %s %s";
		Class<?> clazz = param.getParamClass();

		List<String> distinctFieldNames = findFieldNames(clazz, PhoenixDistinctColumn.class);
		List<String> fieldNames = findFieldNames(clazz, PhoenixColumn.class);
		List<String> params = new ArrayList<String>();
		params.add(String.join(",", distinctFieldNames.size() > 0? distinctFieldNames: fieldNames).toUpperCase());
		params.add(clazz.getAnnotation(PhoenixTable.class).table());
		List<String> conditions = getConditions(param.getModel(), fieldNames);
		if(conditions.size() > 0) {
			params.add("WHERE");
		} else {
			params.add("");
		}
		params.add(String.join(" AND ", conditions));
		params.add(getGroupByCondition(distinctFieldNames));
		params.add(getOrderByCondition(param));
//		params.add(getPaginationConditions(param.getLimit(), param.getOffset()));
		String sql = String.format(format, params.toArray(new Object[params.size()]));

		return sql;
	}
}
