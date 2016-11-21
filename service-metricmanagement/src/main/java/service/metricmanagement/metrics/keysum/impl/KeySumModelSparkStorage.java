package service.metricmanagement.metrics.keysum.impl;

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
import service.metricmanagement.metrics.keysum.model.KeySumMetricModel;

@Service("KeySumModelSparkStorage")
@Transactional
public class KeySumModelSparkStorage  extends SparkSQLTemplate implements Storage<KeySumMetricModel>{

	@Autowired
	public KeySumModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeperContext) throws Exception {
		super(sqlContext, zookeperContext.getZookeepers(), SparkSQLUtil.getTableName(KeySumMetricModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<KeySumMetricModel> get(Param<KeySumMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public KeySumMetricModel save(KeySumMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(KeySumMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
