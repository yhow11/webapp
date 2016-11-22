package sparkapp.collation.receiver.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import service.metricmanagement.metrics.MetricProcessor;
import service.metricmanagement.metrics.param.MetricProcessorParam;
import service.metricmanagement.metricsummary.model.MetricSummaryModel;
import service.segment.processor.VisitorSegmentor;
import service.segment.processor.param.VisitorSegmentorParam;
import sparkapp.collation.receiver.service.LogProcessor;
import sparkapp.collation.receiver.service.VisitorProcessor;
import usertracker.browser.visitorlog.model.VisitorLogModel;
import usertracker.browser.webevent.model.WebEventModel;

@Service("LogReceivedFunction")
@Transactional
public class LogReceivedFunction implements VoidFunction<JavaRDD<VisitorLogModel>>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Resource(name="${LogReceivedFunction.logProcessor}")
	private LogProcessor processor;
	
	@Resource(name="${LogReceivedFunction.visitorProcessor}")
	private VisitorProcessor visitorProcessor;
	
	@Resource
	private List<MetricProcessor> metricProcessors;
	
	@Resource(name="${LogReceivedFunction.visitorSegmentor}")
	private VisitorSegmentor visitorSegmentor;
	
	@Override
	public void call(JavaRDD<VisitorLogModel> rdd) throws Exception {
		// TODO Auto-generated method stub
		List<VisitorLogModel> items = rdd.collect();
		
		if(items.size() > 0) {
			LogMetaData lmd = new LogMetaData(this.getClass().getSimpleName()+".call");
			
			List<WebEventModel> webEvents = processor.process(items, lmd);
			
			List<MetricProcessorParam> metricProcessorParams = new ArrayList<>();
			List<String> visitorIDs = new ArrayList<>();
			for(WebEventModel webEvent: webEvents){
				visitorIDs.add(webEvent.getANONYMOUSVISITORID());
				metricProcessorParams.add(new MetricProcessorParam(webEvent.getANONYMOUSVISITORID(), webEvent.getTYPE(), webEvent.getURL(), webEvent.getELAPSEDTIME()));
			}
			
			List<MetricSummaryModel> summaries = new ArrayList<>();
			for(MetricProcessor processor: metricProcessors){
				summaries.addAll(processor.process(metricProcessorParams, lmd));
			}
			
			List<VisitorSegmentorParam> visitorSegmentorParams = new ArrayList<>();
			for(MetricSummaryModel summary: summaries) {
				visitorSegmentorParams.add(new VisitorSegmentorParam(summary.getVISITORID(), summary.getMETRICID(), summary.getTVALUES()));
			}
			
			
			visitorSegmentor.process(visitorSegmentorParams, lmd);
			visitorProcessor.process(visitorIDs, lmd);
			
		}
		
	}

}
