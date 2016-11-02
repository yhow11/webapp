package service.metricmanagement.metric.impl;

import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Count;
import common.orm.query.param.Param;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import helper.spring.ZookeeperContext;
import service.metricmanagement.metric.model.MetricModel;

@Service("MetricModelSparkCount")
@Transactional
public class MetricModelSparkCount  extends SparkSQLTemplate implements Count<MetricModel> {


	@Autowired
	public MetricModelSparkCount(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(MetricModel.class));
		// TODO Auto-generated constructor stub
	}
	@Override
	public Long count(Param<MetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getCount(param);
	}


}
