package service.metricmanagement.timeonpage.impl;

import java.util.List;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

import common.query.QueryParam;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import service.metricmanagement.timeonpage.TimeOnPageMetricService;
import service.metricmanagement.timeonpage.model.TimeOnPageMetricModel;

import static org.apache.spark.sql.functions.col;
public class TimeOnPageSparkSQLServiceImpl extends SparkSQLTemplate implements TimeOnPageMetricService{

	public TimeOnPageSparkSQLServiceImpl(SQLContext sqlContext, String zookepers) throws Exception {
		super(sqlContext, zookepers, SparkSQLUtil.getTableName(TimeOnPageMetricModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TimeOnPageMetricModel> getAll(QueryParam<TimeOnPageMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<TimeOnPageMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public TimeOnPageMetricModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<TimeOnPageMetricModel> param = new QueryParam<TimeOnPageMetricModel>(TimeOnPageMetricModel.class);
		param.getModel().setVISITORID(id);
		return super.searchOne(param);
	}

	@Override
	public TimeOnPageMetricModel save(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Delete record not supported.");
	}

	@Override
	public TimeOnPageMetricModel get(QueryParam<TimeOnPageMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.searchOne(param);
	}

	@Override
	public TimeOnPageMetricModel getHighest(String visitorID, String metric) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<TimeOnPageMetricModel> param = new QueryParam<>(TimeOnPageMetricModel.class);
		param.getModel().setVISITORID(visitorID);
		param.getModel().setMETRIC(metric);
		DataFrame df = super.getDataFrame(param).orderBy(col("TIMEONPAGE").desc()).limit(1);
		df.show();
		Encoder<TimeOnPageMetricModel> encoder = Encoders.bean(TimeOnPageMetricModel.class);
		Dataset<TimeOnPageMetricModel> dataset = df.as(encoder);
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		return dataset.first();
	}

}
