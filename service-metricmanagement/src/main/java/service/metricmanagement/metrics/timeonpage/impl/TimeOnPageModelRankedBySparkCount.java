package service.metricmanagement.metrics.timeonpage.impl;

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
import service.metricmanagement.metrics.timeonpage.model.TimeOnPageMetricModel;

@Service("TimeOnPageModelRankedBySparkCount")
@Transactional
public class TimeOnPageModelRankedBySparkCount  extends SparkSQLTemplate implements Storage<TimeOnPageMetricModel>{

	@Autowired
	public TimeOnPageModelRankedBySparkCount(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(TimeOnPageMetricModel.class));
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<TimeOnPageMetricModel> get(Param<TimeOnPageMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
//		DefaultParam<TimeOnPageMetricModel> param = new DefaultParam<>();
//		param.getModel().setVISITORID(visitorID);
//		param.getModel().setMETRIC(metric);
		DataFrame df = super.getDataFrame(param).orderBy(col("TIMEONPAGE").desc()).limit(1);
		df.show();
		Encoder<TimeOnPageMetricModel> encoder = Encoders.bean(TimeOnPageMetricModel.class);
		Dataset<TimeOnPageMetricModel> dataset = df.as(encoder);
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		return dataset.collectAsList();
	}

	@Override
	public TimeOnPageMetricModel save(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(TimeOnPageMetricModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	@Override
	public void create() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
