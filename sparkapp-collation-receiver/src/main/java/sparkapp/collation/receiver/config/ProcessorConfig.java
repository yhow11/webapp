package sparkapp.collation.receiver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.pagecount.processor.PageCountProcessor;
import service.timeonpage.processor.TimeOnPageProcessor;

@Configuration
public class ProcessorConfig {

	@Autowired
	private ServiceConfig serviceConfig;
	
	
	@Bean
	public PageCountProcessor pageCountProcessor() throws Exception{
		return new PageCountProcessor(serviceConfig.URLMetricService(), serviceConfig.pageCountService(), serviceConfig.metricSummaryService());
	}
	
	@Bean
	public TimeOnPageProcessor timeOnPageProcessor() throws Exception{
		return new TimeOnPageProcessor(serviceConfig.URLMetricService(), serviceConfig.timeOnPageService(), serviceConfig.metricSummaryService());
	}
}
