package sparkapp.collation.receiver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import service.metricmanagement.MetricProcessor;
import service.metricmanagement.keysum.impl.KeySumMetricProcessorImpl;
import service.metricmanagement.keysum.param.KeySumMetricParam;
import service.metricmanagement.pagecount.impl.PageCountProcessorImpl;
import service.metricmanagement.pagecount.param.PageCountMetricParam;
import service.metricmanagement.timeonpage.impl.TimeOnPageProcessor;
import service.metricmanagement.timeonpage.param.TimeOnPageMetricParam;

@Configuration
public class ProcessorConfig {

	@Autowired
	private ServiceConfig serviceConfig;
	
	@Bean
	public MetricProcessor<KeySumMetricParam> keySumProcessor() throws Exception{
		return new KeySumMetricProcessorImpl(serviceConfig.metricURLService(), serviceConfig.keySumMetricService(), serviceConfig.metricSummaryService());
	}
	
	@Bean
	public MetricProcessor<PageCountMetricParam> pageCountProcessor() throws Exception{
		return new PageCountProcessorImpl(serviceConfig.metricURLService(), serviceConfig.pageCountService(), serviceConfig.metricSummaryService());
	}
	
	@Bean
	public MetricProcessor<TimeOnPageMetricParam> timeOnPageProcessor() throws Exception{
		return new TimeOnPageProcessor(serviceConfig.metricURLService(), serviceConfig.timeOnPageService(), serviceConfig.metricSummaryService());
	}
}
