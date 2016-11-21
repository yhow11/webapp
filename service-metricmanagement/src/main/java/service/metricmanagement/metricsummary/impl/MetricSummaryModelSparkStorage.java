package service.metricmanagement.metricsummary.impl;

import java.util.List;

import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import helper.spring.ZookeeperContext;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryModelSparkStorage")
@Transactional
public class MetricSummaryModelSparkStorage extends SparkSQLTemplate implements Storage<MetricSummaryModel> {

	@Autowired
	public MetricSummaryModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(MetricSummaryModel.class));
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<MetricSummaryModel> get(Param<MetricSummaryModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}
	@Override
	public MetricSummaryModel save(MetricSummaryModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}
	@Override
	public void remove(MetricSummaryModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
