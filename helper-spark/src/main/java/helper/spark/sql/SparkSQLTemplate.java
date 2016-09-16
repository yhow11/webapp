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

import common.query.QueryParam;
import helper.spark.sql.util.SparkSQLUtil;

public abstract class SparkSQLTemplate {

	public static final String TABLE_PROP_KEY = "table";
	public static final String ZOOKEEPER_PROP_KEY = "zkUrl";
	public static final String FORMAT = "org.apache.phoenix.spark";
	
	protected SQLContext sqlContext;
	protected Map<String, String> options;
	
	public SparkSQLTemplate(SQLContext sqlContext, String zookepers, String table) {
		// TODO Auto-generated constructor stub
		this.sqlContext = sqlContext;
		options = new HashMap<String, String>();
		options.put(ZOOKEEPER_PROP_KEY, zookepers);
		options.put(TABLE_PROP_KEY, table);
	}
	
	public <T> List<T> search(QueryParam<T> param) throws Exception {
		sqlContext.read().format(FORMAT).options(options).load().registerTempTable(SparkSQLUtil.getTableName(param));
		DataFrame df = sqlContext.sql(SparkSQLUtil.createGetSQL(param));
		df.withColumn("rownumber", functions.monotonically_increasing_id());
		if(param.getOffset() != null) {
			df = df.filter(col("rownumber").geq(param.getOffset()));
		}
		if(param.getLimit() != null) {
			df = df.filter(col("rownumber").leq(param.getOffset()+param.getLimit()));
		}
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

	public <T> List<T> insert(List<T> values) throws Exception{
		DataFrame pageCountDF = sqlContext.createDataFrame( values, values.get(0).getClass());
		pageCountDF.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite).options(options).save();
		return values;
	}
	
	public <T> T insert(T value) throws Exception{
		DataFrame pageCountDF = sqlContext.createDataFrame( Collections.singletonList(value), value.getClass());
		pageCountDF.write().format("org.apache.phoenix.spark").mode(SaveMode.Overwrite).options(options).save();
		return value;
	}
}
