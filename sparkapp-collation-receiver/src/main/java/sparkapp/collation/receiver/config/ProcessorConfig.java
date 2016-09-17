package sparkapp.collation.receiver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sparkapp.collation.receiver.processor.PageCountProcessor;

@Configuration
public class ProcessorConfig {

	@Autowired
	private ServiceConfig serviceConfig;
	
	
	@Bean
	public PageCountProcessor pageCountProcessor() throws Exception{
		return new PageCountProcessor(serviceConfig.partialPageCountService(), serviceConfig.pageCountService(), serviceConfig.metricSummaryService());
	}
}
