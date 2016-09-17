package sparkapp.collation.receiver.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.spark.sql.functions.col;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

import helper.phoenix.annotation.entity.PhoenixTable;
import service.metricmanagement.enums.MetricTypeEnum;
import service.metricmanagement.model.MetricModel;
import service.urlmanagement.model.URLTaggedModel;
import sparkapp.collation.receiver.object.PartialPageCount;

public class PartialPageCountService {

	private static final String TABLE_PROP_KEY = "table";
	private static final String ZOOKEEPER_PROP_KEY = "zkUrl";
	private static final String FORMAT = "org.apache.phoenix.spark";
	
	private SQLContext sqlContext;
	private Map<String, String> param;
	
	public PartialPageCountService(SQLContext sqlContext, String zookepers) {
		// TODO Auto-generated constructor stub
		this.sqlContext = sqlContext;
		param = new HashMap<String, String>();
		param.put(ZOOKEEPER_PROP_KEY, zookepers);
	}
	
	public List<PartialPageCount> getAll(String url) throws Exception{
		param.put(TABLE_PROP_KEY, URLTaggedModel.class.getAnnotation(PhoenixTable.class).table());
		DataFrame urlTaggedTable = sqlContext.read().format(FORMAT).options(param).load().filter(col("URL").equalTo(url));
		if(urlTaggedTable.count() > 0) {
			param.put(TABLE_PROP_KEY, MetricModel.class.getAnnotation(PhoenixTable.class).table());
			DataFrame metricTable = sqlContext.read().format(FORMAT).options(param).load();
			DataFrame joinTable = urlTaggedTable.join(metricTable, urlTaggedTable.col("TKEY").equalTo(metricTable.col("TKEY")));
			joinTable.filter(col("NAME").equalTo(MetricTypeEnum.PAGE_COUNT.name()));
			Encoder<PartialPageCount> encoder = Encoders.bean(PartialPageCount.class);
			Dataset<PartialPageCount> dataset = joinTable.as(encoder);
			return dataset.collectAsList();
		}
		return new ArrayList<>();
	}
}
