package sparkapp.collation.receiver.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import helper.kafka.service.KafkaService;
import helper.kafka.service.impl.KafkaServiceImpl;
import usertracker.browser.model.AnonymousVisitorModel;
import usertracker.browser.model.BrowserFPModel;
import usertracker.browser.model.DeviceFPModel;
import usertracker.browser.model.SessionModel;
import usertracker.browser.model.VisitorLogModel;
import usertracker.browser.model.WebEventModel;
import usertracker.browser.service.VisitorLogService;
import usertracker.browser.service.impl.VisitorLogServiceImpl;

@Configuration
public class ServiceConfig {

	@Autowired
	private DaoConfig daoConfig;
	
	@Bean
	public VisitorLogService visitorLogService() throws Exception{
		return new VisitorLogServiceImpl(daoConfig.visitorLogDao());
	}
	
	@Bean
	public KafkaService kafkaService() throws Exception{
		return new KafkaServiceImpl("poc:2181", "events");
	}
}
