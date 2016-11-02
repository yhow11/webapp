package sparkapp.collation.receiver.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.orm.query.Storage;
import service.metricmanagement.metrics.MetricProcessor;
import service.metricmanagement.metrics.param.MetricProcessorParam;
import usertracker.browser.visitorlog.model.VisitorLogModel;
import usertracker.browser.webevent.model.WebEventModel;

@Service("LogProcessor")
@Transactional
public class LogProcessor implements VoidFunction<JavaRDD<String>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="${LogProcessor.mapper}")
	private Function<String, VisitorLogModel> mapper;
	
	@Resource(name="${LogProcessor.storage}")
	private Storage<VisitorLogModel> storage;
	
	@Resource(name="${LogProcessor.processor}")
	private Function<List<VisitorLogModel>, List<WebEventModel>> processor;
	
	@Resource
	private List<MetricProcessor> processors;
	
	@Resource(name="${LogProcessor.activeVisitorModelProcessor}")
	private VoidFunction<Object> activeVisitorModelProcessor;
	@Override
	public void call(JavaRDD<String> linesRDD) throws Exception {
		// TODO Auto-generated method stub
		
		JavaRDD<VisitorLogModel> rowRDD = linesRDD.map(mapper);

		List<VisitorLogModel> visitorLogs = rowRDD.collect();
		
		storage.save(visitorLogs);
		
		List<WebEventModel> webEvents = processor.call(visitorLogs);
		
		
		processors.forEach(processor -> {
			for(WebEventModel webEvent: webEvents){
				processor.process(new MetricProcessorParam(webEvent.getANONYMOUSVISITORID(), webEvent.getTYPE(), webEvent.getURL(), webEvent.getELAPSEDTIME()));
			}
		});
		
		webEvents.forEach(item ->{
			try {
				activeVisitorModelProcessor.call(item.getANONYMOUSVISITORID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}

}
