package sparkapp.collation.receiver.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.Window;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.param.DefaultParam;
import common.orm.query.param.Param;
import helper.spark.DFStorage;
import helper.spark.sql.util.SparkSQLUtil;
import scala.Tuple2;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;
import usertracker.browser.visitor.model.ActiveVisitorModel;
import usertracker.browser.webevent.model.WebEventModel;

@Service("LogReceiverTest")
@Transactional
public class LogReceiverTest implements VoidFunction<String> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="${ActiveVisitorModelProcessor.webEventStorage}")
	private DFStorage<WebEventModel> webEventStorage;

	@Resource(name="${ActiveVisitorModelProcessor.metricSummaryStorage}")
	private DFStorage<MetricSummaryModel> metricSummaryStorage;

	public LogReceiverTest() {

	}

	@Override
	public void call(String t) throws Exception {
		// TODO Auto-generated method stub
		Param<WebEventModel> param= new DefaultParam<>(WebEventModel.class);
		List<Column> columns = SparkSQLUtil.getColumns(param.getModelClass());
		columns.add(functions.row_number().over(Window.partitionBy("ANONYMOUSVISITORID").orderBy("TIMESTAMP")).as("ROWNUMBERBYID"));
		DataFrame df =webEventStorage.get(param).select(SparkSQLUtil.convert(columns));
		JavaPairRDD<String, ActiveVisitorModel> rdd1= df.select(SparkSQLUtil.convert(columns)).where(df.col("ROWNUMBERBYID").leq(5)).javaRDD().map(row->{
			ActiveVisitorModel visitor = new ActiveVisitorModel();
			visitor.setId(row.getString(1));
			visitor.setTimestamp(row.getLong(4));
			visitor.setActivities(row.getString(5)+" on "+row.getString(6));
			return visitor;
		}).mapToPair(model->{
			return new Tuple2<String, ActiveVisitorModel>(model.getId(), model);
		}).reduceByKey((model1, model2) ->{
			ActiveVisitorModel visitor = new ActiveVisitorModel();
			visitor.setId(model1.getId());
			if(model1.getTimestamp() > model2.getTimestamp()) {
				visitor.setTimestamp(model1.getTimestamp());
			} else {
				visitor.setTimestamp(model2.getTimestamp());
			}
			visitor.setActivities(model1.getActivities() + "," + model2.getActivities());
			return visitor;
		});
		
		JavaPairRDD<String, ActiveVisitorModel> rdd2= metricSummaryStorage.get(new DefaultParam<>(MetricSummaryModel.class)).javaRDD().map(row ->{
			ActiveVisitorModel visitor = new ActiveVisitorModel();
			visitor.setId(row.getString(0));
			visitor.setMetrics(row.getString(1) +":"+ row.getString(3));
			return visitor;
		}).mapToPair(model ->{
			return new Tuple2<String, ActiveVisitorModel>(model.getId(), model);
		}).reduceByKey((model1, model2) ->{
			ActiveVisitorModel visitor = new ActiveVisitorModel();
			visitor.setId(model1.getId());
			visitor.setMetrics(model1.getMetrics() +","+ model2.getMetrics());
			return visitor;
		});
		
		rdd1.join(rdd2).map(tuple->{
			ActiveVisitorModel visitor = new ActiveVisitorModel();
			visitor.setId(tuple._1());
			visitor.setTimestamp(tuple._2()._1().getTimestamp());
			visitor.setMetrics(tuple._2()._2().getMetrics());
			visitor.setActivities(tuple._2()._1().getActivities());
			return visitor;
		}).foreach(item ->{
			System.out.println(item.getId() +"  "+ item.getTimestamp() + "   "+ item.getMetrics() +"   "+item.getActivities());;
		});
	}
}
