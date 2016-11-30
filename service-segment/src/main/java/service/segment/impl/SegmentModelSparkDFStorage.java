package service.segment.impl;

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
import service.segment.model.SegmentModel;

@Service("SegmentModelSparkDFStorage")
@Transactional
public class SegmentModelSparkDFStorage extends SparkSQLTemplate implements DFStorage<SegmentModel> {

	@Autowired
	public SegmentModelSparkDFStorage(SQLContext sqlContext, ZookeeperContext zookeeperContext) throws Exception {
		super(sqlContext, zookeeperContext.getZookeepers(), SparkSQLUtil.getTableName(SegmentModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataFrame get(Param<SegmentModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.getDataFrame(param);
	}

	@Override
	public void save(DataFrame df) throws Exception {
		// TODO Auto-generated method stub
		super.save(df);
	}

}
