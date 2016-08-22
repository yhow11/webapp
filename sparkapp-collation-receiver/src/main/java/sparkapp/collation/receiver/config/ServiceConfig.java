package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import helper.kafka.service.KafkaService;
import helper.kafka.service.impl.KafkaServiceImpl;
import sparkapp.collation.receiver.service.ReceiverService;
import sparkapp.collation.receiver.service.impl.ReceiverServiceImpl;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.DeviceFPService;
import usertracker.browser.service.SessionService;
import usertracker.browser.service.VisitorLogService;
import usertracker.browser.service.WebEventService;
import usertracker.browser.service.impl.AnonymousVisitorServiceImpl;
import usertracker.browser.service.impl.BrowserFPServiceImpl;
import usertracker.browser.service.impl.DeviceFPServiceImpl;
import usertracker.browser.service.impl.SessionServiceImpl;
import usertracker.browser.service.impl.VisitorLogServiceImpl;
import usertracker.browser.service.impl.WebEventServiceImpl;

@Configuration
@PropertySource({"classpath:kafka.properties", "classpath:spark.properties"})
public class ServiceConfig {

	@Value("${kafka.topics}")
	private String topics;
	
	@Value("${kafka.metadata.broker.list}")
	private String metaDataBrokerList;
	
	@Autowired
	private DaoConfig daoConfig;
	
	@Bean
	public VisitorLogService visitorLogService() throws Exception{
		return new VisitorLogServiceImpl(daoConfig.phoenixDaoImpl());
	}
	@Bean
	public WebEventService webEventService() throws Exception{
		return new WebEventServiceImpl(daoConfig.phoenixDaoImpl());
	}
	
	@Bean
	public SessionService sessionService() throws Exception{
		return new SessionServiceImpl(daoConfig.phoenixDaoImpl());
	}
	
	@Bean
	public AnonymousVisitorService anonymousVisitorService() throws Exception{
		return new AnonymousVisitorServiceImpl(daoConfig.phoenixDaoImpl());
	}
	
	@Bean
	public BrowserFPService browserFPService() throws Exception{
		return new BrowserFPServiceImpl(daoConfig.phoenixDaoImpl());
	}
	
	@Bean
	public DeviceFPService deviceFPService() throws Exception{
		return new DeviceFPServiceImpl(daoConfig.phoenixDaoImpl());
	}
	
	@Bean
	public ReceiverService receiverService() throws Exception {
		return new ReceiverServiceImpl(visitorLogService(), anonymousVisitorService(), webEventService(),
				browserFPService(), deviceFPService(), sessionService());
	}
	
	@Bean
	public KafkaService kafkaService() throws Exception{
		return new KafkaServiceImpl(metaDataBrokerList, topics);
	}
	
	@Bean
	public RestTemplate restTemplateService(){
		RestTemplate rt = new RestTemplate();

		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		rt.getMessageConverters().add(new StringHttpMessageConverter());
		return rt;
	}
}
