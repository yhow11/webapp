package service.metricmanagement.metricsummary.impl;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.param.Param;
import helper.spark.DFStorage;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import helper.spring.ZookeeperContext;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;

@Service("MetricSummaryModelSparkDFStorage")
@Transactional
public class MetricSummaryModelSparkDFStorage extends SparkSQLTemplate implements DFStorage<MetricSummaryModel> {

	@Autowired
	public MetricSummaryModelSparkDFStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(MetricSummaryModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataFrame get(Param<MetricSummaryModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getDataFrame(param);
	}

}
