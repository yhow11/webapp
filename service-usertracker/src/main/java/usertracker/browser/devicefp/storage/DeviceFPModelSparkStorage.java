package usertracker.browser.devicefp.storage;

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
import usertracker.browser.devicefp.model.DeviceFPModel;

@Service("DeviceFPModelSparkStorage")
@Transactional
public class DeviceFPModelSparkStorage extends SparkSQLTemplate implements Storage<DeviceFPModel> {
	
	@Autowired
	public DeviceFPModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(DeviceFPModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DeviceFPModel> get(Param<DeviceFPModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public DeviceFPModel save(DeviceFPModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(DeviceFPModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	
}
