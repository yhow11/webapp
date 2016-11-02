package service.metricmanagement.metrics.keysum.impl;

import static org.apache.spark.sql.functions.col;

import java.util.List;
import java.util.Optional;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.aggregator.Sum;
import common.orm.query.param.Param;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import helper.spring.ZookeeperContext;
import service.metricmanagement.metrics.keysum.model.KeySumMetricModel;
import service.metricmanagement.metrics.pagecount.model.PageCountModel;

@Service("KeySumModelSparkSum")
@Transactional
public class KeySumModelSparkSum extends SparkSQLTemplate implements Sum<KeySumMetricModel>{

	@Autowired
	public KeySumModelSparkSum(SQLContext sqlContext, ZookeeperContext zookeperContext) throws Exception {
		super(sqlContext, zookeperContext.getZookeepers(), SparkSQLUtil.getTableName(PageCountModel.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long sum(Param<KeySumMetricModel> param) throws Exception {
		// TODO Auto-generated method stub
//		DefaultParam<KeySumMetricModel> param = new DefaultParam<>();
//		param.getModel().setVISITORID(visitorID);
//		param.getModel().setMETRIC(metric);
		DataFrame df = super.getDataFrame(param).groupBy(
			col("VISITORID"), col("URL"), col("METRIC"), col("TKEY")
		).agg(org.apache.spark.sql.functions.sum(col("TVALUES").alias("TOTAL")));
		List<Row> result  = df.collectAsList();
		sqlContext.dropTempTable(SparkSQLUtil.getTableName(param));
		Optional<Row> row = result.stream().findFirst();
		if(row.isPresent()) {
			return Double.valueOf(String.valueOf(row.get().get(4))).longValue();
		} else {
			return 0L;
		}
		
	}

}
