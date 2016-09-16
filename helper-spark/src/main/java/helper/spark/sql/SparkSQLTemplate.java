package helper.spark.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

import common.query.QueryParam;
import helper.phoenix.util.PhoenixUtil;
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
		sqlContext.read().format(FORMAT).options(options).load().registerTempTable(PhoenixUtil.getTableName(param));
		DataFrame df = sqlContext.sql(SparkSQLUtil.createGetSQL(param));
		Encoder<T> encoder = Encoders.bean(param.getModelClass());
		Dataset<T> dataset = df.as(encoder);
		sqlContext.dropTempTable(PhoenixUtil.getTableName(param));
		return dataset.collectAsList();
	}
}
