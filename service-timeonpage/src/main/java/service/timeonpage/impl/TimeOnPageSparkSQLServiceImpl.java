package service.timeonpage.impl;

import java.util.List;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SQLContext;

import common.query.QueryParam;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import service.timeonpage.TimeOnPageService;
import service.timeonpage.model.TimeOnPageModel;

import static org.apache.spark.sql.functions.col;
public class TimeOnPageSparkSQLServiceImpl extends SparkSQLTemplate implements TimeOnPageService{

	public TimeOnPageSparkSQLServiceImpl(SQLContext sqlContext, String zookepers, Class<?> modelClass) throws Exception {
		super(sqlContext, zookepers, SparkSQLUtil.getTableName(modelClass));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TimeOnPageModel> getAll(QueryParam<TimeOnPageModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<TimeOnPageModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public TimeOnPageModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<TimeOnPageModel> param = new QueryParam<TimeOnPageModel>(TimeOnPageModel.class);
		param.getModel().setVISITORID(id);
		return super.searchOne(param);
	}

	@Override
	public TimeOnPageModel save(TimeOnPageModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(TimeOnPageModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Delete record not supported.");
	}

	@Override
	public TimeOnPageModel get(QueryParam<TimeOnPageModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.searchOne(param);
	}

	@Override
	public TimeOnPageModel getHighest(String visitorID, String metric) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<TimeOnPageModel> param = new QueryParam<>(TimeOnPageModel.class);
		param.getModel().setVISITORID(visitorID);
		param.getModel().setMETRIC(metric);
		DataFrame df = super.getDataFrame(param).orderBy(col("TIMEONPAGE").desc()).limit(1);
		df.show();
		Encoder<TimeOnPageModel> encoder = Encoders.bean(TimeOnPageModel.class);
		Dataset<TimeOnPageModel> dataset = df.as(encoder);
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		return dataset.first();
	}

}
