package helper.spark.sql;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.spark.sql.functions;
import static org.apache.spark.sql.functions.col;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.catalyst.expressions.MonotonicallyIncreasingID;
import org.hibernate.SQLQuery;

import common.query.QueryParam;
import helper.phoenix.util.PhoenixUtil;
import helper.spark.sql.util.SparkSQLUtil;

public abstract class SparkSQLTemplate {

	public static final String TABLE_PROP_KEY = "table";
	public static final String ZOOKEEPER_PROP_KEY = "zkUrl";
	public static final String FORMAT = "org.apache.phoenix.spark";
	
	protected SQLContext sqlContext;
	protected Map<String, String> options;
	protected String table;
	
	public SparkSQLTemplate(SQLContext sqlContext, String zookepers, String table) {
		// TODO Auto-generated constructor stub
		this.sqlContext = sqlContext;
		options = new HashMap<String, String>();
		options.put(ZOOKEEPER_PROP_KEY, zookepers);
		options.put(TABLE_PROP_KEY, table);
		this.table = table;
	}
	private <T> DataFrame getDataFrame(QueryParam<T> param) throws Exception {
		sqlContext.read().format(FORMAT).options(options).load().registerTempTable(SparkSQLUtil.getTableName(param));
		DataFrame df = sqlContext.sql(SparkSQLUtil.createGetSQL(param));
		List<Column> columns = SparkSQLUtil.getColumns(param.getModelClass());
		columns.add(functions.monotonically_increasing_id().as("PAGINATIONNUMBER"));
		df = df.select(SparkSQLUtil.convert(columns));
		df.show();
		if(param.getOffset() != null) {
			df = df.filter(col("PAGINATIONNUMBER").geq(param.getOffset()));
		}
		if(param.getLimit() != null) {
			param.setOffset(param.getOffset() != null? param.getOffset():0L);
			df = df.filter(col("PAGINATIONNUMBER").leq(param.getOffset()+param.getLimit()));
		}
		return df;
	}
	public <T> List<T> search(QueryParam<T> param) throws Exception {
		DataFrame df = getDataFrame(param);
		Encoder<T> encoder = Encoders.bean(param.getModelClass());
		Dataset<T> dataset = df.as(encoder);
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		return dataset.collectAsList();
	}
	public <T> T searchOne(QueryParam<T> param) throws Exception {
		param.setLimit(1L);
		List<T> results = search(param);
		if(results.size() > 0){
			return results.get(0);
		}
		return null;
		
	}

	public <T> List<T> insert(List<T> models) throws Exception{
		DataFrame pageCountDF = sqlContext.createDataFrame( models, models.get(0).getClass());
		pageCountDF.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite).options(options).save();
		return models;
	}
	
	public <T> T insert(T model) throws Exception{
		DataFrame pageCountDF = sqlContext.createDataFrame( Collections.singletonList(model), model.getClass());
		pageCountDF.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite).options(options).save();
		return model;
	}
	
	public Long count(QueryParam<?> param) throws Exception {
		DataFrame df = getDataFrame(param);
		return df.count();
	}
}
