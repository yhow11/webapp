package sparkapp.collation.receiver.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.LogMetaData;
import service.metricmanagement.metrics.MetricProcessor;
import service.metricmanagement.metrics.param.MetricProcessorParam;
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
	
	@Override
	public void call(JavaRDD<VisitorLogModel> rdd) throws Exception {
		// TODO Auto-generated method stub
		List<VisitorLogModel> items = rdd.collect();
		
		if(items.size() > 0) {
			LogMetaData lmd = new LogMetaData(this.getClass().getSimpleName()+".call");
			
			List<WebEventModel> webEvents = processor.process(items, lmd);
			
			for(WebEventModel webEvent: webEvents){
				for(MetricProcessor processor: metricProcessors){
					processor.process(new MetricProcessorParam(webEvent.getANONYMOUSVISITORID(), webEvent.getTYPE(), webEvent.getURL(), webEvent.getELAPSEDTIME()), lmd);
				}
				visitorProcessor.process(webEvent.getANONYMOUSVISITORID(), lmd);
			}
		}
		
	}

}
