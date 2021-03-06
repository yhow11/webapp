package usertracker.browser.visitor.impl;

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
import usertracker.browser.visitor.model.ActiveVisitorModel;

@Service("ActiveVisitorModelSparkStorage")
@Transactional
public class ActiveVisitorModelSparkStorage extends SparkSQLTemplate implements Storage<ActiveVisitorModel> {
	
	@Autowired
	public ActiveVisitorModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(ActiveVisitorModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ActiveVisitorModel> get(Param<ActiveVisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public ActiveVisitorModel save(ActiveVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(ActiveVisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	

}
