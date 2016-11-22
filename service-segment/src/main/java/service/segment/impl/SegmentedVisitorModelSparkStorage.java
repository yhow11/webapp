package service.segment.impl;

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
import service.segment.model.SegmentedVisitorModel;

@Service("SegmentedVisitorModelSparkStorage")
@Transactional
public class SegmentedVisitorModelSparkStorage  extends SparkSQLTemplate implements Storage<SegmentedVisitorModel>{

	@Autowired
	public SegmentedVisitorModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(SegmentedVisitorModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<SegmentedVisitorModel> get(Param<SegmentedVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public SegmentedVisitorModel save(SegmentedVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(SegmentedVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
