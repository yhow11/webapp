package usertracker.browser.browserfp.storage;

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
import usertracker.browser.browserfp.model.BrowserFPModel;

@Service("BrowserFPModelSparkStorage")
@Transactional
public class BrowserFPModelSparkStorage extends SparkSQLTemplate implements Storage<BrowserFPModel> {
	
	@Autowired
	public BrowserFPModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(BrowserFPModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BrowserFPModel> get(Param<BrowserFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public BrowserFPModel save(BrowserFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(BrowserFPModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}


	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}
}
