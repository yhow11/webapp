package service.metricmanagement.keysum.impl;

import static org.apache.spark.sql.functions.col;

import java.util.List;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

import common.query.QueryParam;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import service.metricmanagement.keysum.KeySumMetricService;
import service.metricmanagement.keysum.model.KeySumMetricModel;

public class KeySumMetricSparkSQLServiceImpl extends SparkSQLTemplate implements KeySumMetricService{

	public KeySumMetricSparkSQLServiceImpl(SQLContext sqlContext, String zookepers) throws Exception {
		super(sqlContext, zookepers, SparkSQLUtil.getTableName(KeySumMetricModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<KeySumMetricModel> getAll(QueryParam<KeySumMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<KeySumMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public KeySumMetricModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<KeySumMetricModel> param = new QueryParam<KeySumMetricModel>(KeySumMetricModel.class);
		param.getModel().setVISITORID(id);
		return super.searchOne(param);
	}

	@Override
	public KeySumMetricModel save(KeySumMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(KeySumMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Delete record not supported.");
	}

	@Override
	public KeySumMetricModel get(QueryParam<KeySumMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.searchOne(param);
	}

	@Override
	public Long getSum(String visitorID, String metric) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<KeySumMetricModel> param = new QueryParam<>(KeySumMetricModel.class);
		param.getModel().setVISITORID(visitorID);
		param.getModel().setMETRIC(metric);
		DataFrame df = super.getDataFrame(param).groupBy(
			col("VISITORID"), col("URL"), col("METRIC"), col("TKEY")
		).agg(org.apache.spark.sql.functions.sum(col("TVALUES").as("TOTAL")));
		df.show();
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		return 0L;
	}

}
