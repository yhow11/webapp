package usertracker.browser.visitorlog.impl;

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
import usertracker.browser.visitorlog.model.VisitorLogModel;

@Service("VisitorLogModelSparkStorage")
@Transactional
public class VisitorLogModelSparkStorage extends SparkSQLTemplate implements Storage<VisitorLogModel> {
	
	@Autowired
	public VisitorLogModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(VisitorLogModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<VisitorLogModel> get(Param<VisitorLogModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public VisitorLogModel save(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(VisitorLogModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	
	
}
