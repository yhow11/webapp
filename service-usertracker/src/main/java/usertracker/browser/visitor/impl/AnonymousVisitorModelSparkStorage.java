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
import usertracker.browser.visitor.model.VisitorModel;

@Service("AnonymousVisitorModelSparkStorage")
@Transactional
public class AnonymousVisitorModelSparkStorage extends SparkSQLTemplate implements Storage<VisitorModel> {
	
	@Autowired
	public AnonymousVisitorModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(VisitorModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<VisitorModel> get(Param<VisitorModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public VisitorModel save(VisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(VisitorModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	

}
