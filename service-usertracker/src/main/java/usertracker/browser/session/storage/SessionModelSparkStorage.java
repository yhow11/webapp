package usertracker.browser.session.storage;

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
import usertracker.browser.session.model.SessionModel;

@Service("SessionModelSparkStorage")
@Transactional
public class SessionModelSparkStorage extends SparkSQLTemplate implements Storage<SessionModel> {
	
	@Autowired
	public SessionModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(SessionModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SessionModel> get(Param<SessionModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public SessionModel save(SessionModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(SessionModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}


	
}
