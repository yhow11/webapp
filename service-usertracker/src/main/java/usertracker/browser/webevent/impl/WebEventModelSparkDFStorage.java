package usertracker.browser.webevent.impl;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.param.Param;
import helper.spark.DFStorage;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import helper.spring.ZookeeperContext;
import usertracker.browser.webevent.model.WebEventModel;

@Service("WebEventModelSparkDFStorage")
@Transactional
public class WebEventModelSparkDFStorage extends SparkSQLTemplate implements DFStorage<WebEventModel> {

	@Autowired
	public WebEventModelSparkDFStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(WebEventModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataFrame get(Param<WebEventModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getDataFrame(param);
	}


}
