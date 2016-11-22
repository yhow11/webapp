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
import service.segment.model.SegmentModel;

@Service("SegmentModelSparkStorage")
@Transactional
public class SegmentModelSparkStorage  extends SparkSQLTemplate implements Storage<SegmentModel>{

	@Autowired
	public SegmentModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(SegmentModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<SegmentModel> get(Param<SegmentModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public SegmentModel save(SegmentModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(SegmentModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
