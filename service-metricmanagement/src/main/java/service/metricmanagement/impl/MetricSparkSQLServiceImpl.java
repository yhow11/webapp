package service.metricmanagement.impl;

import java.util.List;

import org.apache.pig.backend.executionengine.ExecException;
import org.apache.spark.sql.SQLContext;

import common.query.QueryParam;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import service.metricmanagement.MetricService;
import service.metricmanagement.model.MetricModel;

public class MetricSparkSQLServiceImpl extends SparkSQLTemplate implements MetricService {


	public MetricSparkSQLServiceImpl(SQLContext sqlContext, String zookepers, Class<?> modelClass) throws Exception {
		super(sqlContext, zookepers, SparkSQLUtil.getTableName(modelClass));
		// TODO Auto-generated constructor stub
	}

	public List<MetricModel> getAll(QueryParam<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	public Long getCount(QueryParam<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
//		return super.count(param);
		return null;
	}

	public MetricModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.getModel().setID(Long.valueOf(id));
		return super.searchOne(param);
	}

	public MetricModel save(MetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	public void remove(MetricModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Delete record not supported.");
	}

	public List<MetricModel> getAll(Long offset, Long limit) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.setLimit(limit);
		param.setOffset(offset);
		return super.search(param);
	}

	public List<MetricModel> getAll(String key) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<MetricModel> param = new QueryParam<MetricModel>(MetricModel.class);
		param.getModel().setTKEY(key);
		return super.search(param);
	}

}
