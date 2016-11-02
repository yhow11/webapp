package service.metricmanagement.metrics.pagecount.impl;

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
import service.metricmanagement.metrics.pagecount.model.PageCountModel;

@Service("PageCountModelSparkStorage")
@Transactional
public class PageCountModelSparkStorage  extends SparkSQLTemplate implements Storage<PageCountModel>{

	@Autowired
	public PageCountModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(PageCountModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<PageCountModel> get(Param<PageCountModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public PageCountModel save(PageCountModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(PageCountModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
