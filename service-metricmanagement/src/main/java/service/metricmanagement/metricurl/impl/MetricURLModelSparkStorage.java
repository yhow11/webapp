package service.metricmanagement.metricurl.impl;

import static org.apache.spark.sql.functions.col;

import java.util.ArrayList;
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
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.spark.sql.SparkSQLTemplate;
import helper.spring.ZookeeperContext;
import service.metricmanagement.metric.model.MetricModel;
import service.metricmanagement.metricurl.model.MetricURLModel;
import service.urlmanagement.urltagged.model.URLTaggedModel;

@Service("MetricURLModelSparkStorage")
@Transactional
public class MetricURLModelSparkStorage  extends SparkSQLTemplate implements Storage<MetricURLModel> {
	
	@Autowired
	public MetricURLModelSparkStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), "");
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<MetricURLModel> get(Param<MetricURLModel> param) throws Exception {
		// TODO Auto-generated method stub
		options.put(TABLE_PROP_KEY, URLTaggedModel.class.getAnnotation(PhoenixTable.class).table());
		DataFrame urlTaggedTable = sqlContext.read().format(FORMAT).options(options).load().filter(col("URL").equalTo(param.getModel().getURL()));
		urlTaggedTable.show();
		if(urlTaggedTable.count() > 0) {
			options.put(TABLE_PROP_KEY, MetricModel.class.getAnnotation(PhoenixTable.class).table());
			DataFrame metricTable = sqlContext.read().format(FORMAT).options(options).load();
			DataFrame joinTable = urlTaggedTable.join(metricTable, urlTaggedTable.col("TKEY").equalTo(metricTable.col("TKEY")));
			joinTable = joinTable.filter(metricTable.col("TYPE").equalTo(param.getModel().getMETRICTYPE()))
			.select(urlTaggedTable.col("URL"), urlTaggedTable.col("TKEY"), urlTaggedTable.col("TVALUES"), metricTable.col("NAME").as("METRIC"),metricTable.col("TYPE").as("METRICTYPE") );
			joinTable.show();
			Encoder<MetricURLModel> encoder = Encoders.bean(MetricURLModel.class);
			Dataset<MetricURLModel> dataset = joinTable.as(encoder);
			return dataset.collectAsList();
		}
		return new ArrayList<>();
	}
	@Override
	public MetricURLModel save(MetricURLModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}
	@Override
	public void remove(MetricURLModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}
	@Override
	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
