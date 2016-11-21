package service.metricmanagement.metrics.timeonpage.impl;

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
import service.metricmanagement.metrics.timeonpage.model.TimeOnPageMetricModel;

@Service("TimeOnPageModelSparkStorage")
@Transactional
public class TimeOnPageModelSparkStorage  extends SparkSQLTemplate implements Storage<TimeOnPageMetricModel>{

	@Autowired
	public TimeOnPageModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(TimeOnPageMetricModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<TimeOnPageMetricModel> get(Param<TimeOnPageMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public TimeOnPageMetricModel save(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
