package service.metricmanagement.metrics.pagecount.impl;

import static org.apache.spark.sql.functions.col;

import java.util.List;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
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

@Service("PageCountModelRankedByCountSparkStorage")
@Transactional
public class PageCountModelRankedByCountSparkStorage  extends SparkSQLTemplate implements Storage<PageCountModel>{

	
	@Autowired
	public PageCountModelRankedByCountSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(PageCountModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<PageCountModel> get(Param<PageCountModel> param) throws Exception {
		// TODO Auto-generated method stub
//		Param<PageCountModel> param = new PageCountModelParam<>();
//		param.getModel().setVISITORID(visitorID);
//		param.getModel().setMETRIC(metric);
		DataFrame df = super.getDataFrame(param).orderBy(col("TCOUNT").desc()).limit(1);
		Encoder<PageCountModel> encoder = Encoders.bean(PageCountModel.class);
		Dataset<PageCountModel> dataset = df.as(encoder);
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		return dataset.collectAsList();
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
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
