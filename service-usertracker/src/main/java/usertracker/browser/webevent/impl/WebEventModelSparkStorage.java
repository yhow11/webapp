package usertracker.browser.webevent.impl;

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
import usertracker.browser.webevent.model.WebEventModel;

@Service("WebEventModelSparkStorage")
@Transactional
public class WebEventModelSparkStorage extends SparkSQLTemplate implements Storage<WebEventModel> {
	
	@Autowired
	public WebEventModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(WebEventModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<WebEventModel> get(Param<WebEventModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public WebEventModel save(WebEventModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(WebEventModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	
}
