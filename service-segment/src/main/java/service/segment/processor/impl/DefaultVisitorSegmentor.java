package service.segment.processor.impl;

import javax.annotation.Resource;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import common.orm.query.Storage;
import common.orm.query.param.DefaultParam;
import helper.spark.DFStorage;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;
import service.segment.model.SegmentModel;
import service.segment.model.SegmentedVisitorModel;
import service.segment.processor.VisitorSegmentor;
import service.segment.processor.param.VisitorSegmentorParam;

@Service("DefaultVisitorSegmentor")
@Transactional
public class DefaultVisitorSegmentor implements VisitorSegmentor {

	@Resource(name="${DefaultVisitorSegmentor.segmentedVisitorStorage}")
	private Storage<SegmentedVisitorModel> segmentedVisitorStorage;
	@Resource(name="${DefaultVisitorSegmentor.segmentedVisitorDFStorage}")
	private DFStorage<SegmentedVisitorModel> segmentedVisitorDFStorage;
	@Resource(name="${DefaultVisitorSegmentor.segmentStorage}")
	private DFStorage<SegmentModel> segmentStorage;
	@Resource(name="${DefaultVisitorSegmentor.metricSummaryStorage}")
	private DFStorage<MetricSummaryModel> metricSummaryStorage;
	@Override
	public Boolean process(VisitorSegmentorParam param, LogMetaData lmd) throws Exception{
		// TODO Auto-generated method stub
		DataFrame segmentDF = segmentStorage.get(new DefaultParam<>(SegmentModel.class));
		
		DataFrame metricSummaryDF = metricSummaryStorage.get(new DefaultParam<>(MetricSummaryModel.class));
		
		DataFrame joinDF = metricSummaryDF.join(segmentDF, metricSummaryDF.col("METRICID").equalTo(segmentDF.col("METRICID")));
		
		joinDF = joinDF.select(metricSummaryDF.col("VISITORID"), metricSummaryDF.col("TVALUES").as("METRICVALUE"), segmentDF.col("ID").as("SEGMENTID"), segmentDF.col("TVALUE").as("SEGMENTVALUE"), segmentDF.col("FILTER"));
		
		joinDF = joinDF.withColumn("EQ", org.apache.spark.sql.functions.when(joinDF.col("FILTER").equalTo("EQ"), 
				org.apache.spark.sql.functions.when(joinDF.col("METRICVALUE").equalTo(joinDF.col("SEGMENTVALUE")), "TRUE")));
		joinDF = joinDF.withColumn("NE", org.apache.spark.sql.functions.when(joinDF.col("FILTER").equalTo("NE"), 
				org.apache.spark.sql.functions.when(joinDF.col("METRICVALUE").notEqual(joinDF.col("SEGMENTVALUE")), "TRUE")));
		joinDF = joinDF.withColumn("GT", org.apache.spark.sql.functions.when(joinDF.col("FILTER").equalTo("GT"), 
				org.apache.spark.sql.functions.when(joinDF.col("METRICVALUE").cast(DataTypes.LongType).gt(joinDF.col("SEGMENTVALUE").cast(DataTypes.LongType)), "TRUE")));
		joinDF = joinDF.withColumn("LT", org.apache.spark.sql.functions.when(joinDF.col("FILTER").equalTo("LT"), 
				org.apache.spark.sql.functions.when(joinDF.col("METRICVALUE").cast(DataTypes.LongType).lt(joinDF.col("SEGMENTVALUE").cast(DataTypes.LongType)), "TRUE")));
		joinDF.printSchema();
		joinDF.show();
		joinDF = joinDF.filter(joinDF.col("EQ").equalTo("TRUE").or(joinDF.col("NE").equalTo("TRUE")).or(joinDF.col("GT").equalTo("TRUE")).or(joinDF.col("LT").equalTo("TRUE")));

		joinDF = joinDF.select(joinDF.col("VISITORID"),joinDF.col("SEGMENTID").cast(DataTypes.StringType));
		
//		DataFrame groupBySegmentDF = joinDF.select(joinDF.col("SEGMENTID")).distinct();
//		for(Row row: groupBySegmentDF.collect()){
//			SegmentedVisitorModel model = new SegmentedVisitorModel();
//			model.setSEGMENTID(row.getString(0));
//			segmentedVisitorStorage.remove(model);
//		}
		segmentedVisitorStorage.createTable();
		
		segmentedVisitorDFStorage.save(joinDF);
		//		joinDF.filter(joinDF.col("FILTER").equalTo("EQ")).filter(joinDF.col("METRICVALUE").equalTo(joinDF.col("SEGMENTVALUE")));
		
		
//		JavaRDD<Map<String, Object>> joinRDD = joinDF.select(metricSummaryDF.col("VISITORID"), metricSummaryDF.col("TVALUES").as("METRICVALUE"), segmentDF.col("ID").as("SEGMENTID"), segmentDF.col("TVALUE").as("SEGMENTVALUE"), segmentDF.col("FILTER")).javaRDD().map(item->{
//			Map<String, Object> map = new HashMap<>();
//			map.put("VISITORID", item.get(0));
//			map.put("METRICVALUE", item.get(1));
//			map.put("SEGMENTID", item.get(2));
//			map.put("SEGMENTVALUE", item.get(3));
//			map.put("FILTER", item.get(4));
//			return map;
//		});
//		
//		JavaRDD<Map<String, Object>> filteredRDD = joinRDD.filter(item ->{
//			if(item.get("FILTER").equals("EQ")){
//				return item.get("METRICVALUE").equals(item.get("SEGMENTVALUE"));
//			} else if(item.get("FILTER").equals("NE")){
//				return !item.get("METRICVALUE").equals(item.get("SEGMENTVALUE"));
//			} else if(item.get("FILTER").equals("LT")){
//				return Long.valueOf(String.valueOf(item.get("METRICVALUE"))) < Long.valueOf(String.valueOf(item.get("SEGMENTVALUE")));
//			}  else if(item.get("FILTER").equals("GT")){
//				return Long.valueOf(String.valueOf(item.get("METRICVALUE"))) > Long.valueOf(String.valueOf(item.get("SEGMENTVALUE")));
//			}
//			return false;
//		});
//		
//		
//		JavaRDD<SegmentedVisitorModel> segmentedRDD = filteredRDD.map(item ->{
//			SegmentedVisitorModel segmentedVisitor = new SegmentedVisitorModel();
//			segmentedVisitor.setSEGMENTID(String.valueOf(item.get("SEGMENTID")));
//			segmentedVisitor.setVISITORID(String.valueOf(item.get("VISITORID")));
//			return segmentedVisitor;
//		});
//		
//		segmentedVisitorStorage.save(segmentedRDD.collect());
		return true;
	}
}
