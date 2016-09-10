package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import sparkapp.collation.receiver.service.ReceiverService;
import sparkapp.collation.receiver.service.impl.ReceiverServiceImpl;
import usertracker.browser.service.AnonymousVisitorService;
import usertracker.browser.service.BrowserFPService;
import usertracker.browser.service.DeviceFPService;
import usertracker.browser.service.SessionService;
import usertracker.browser.service.VisitorLogService;
import usertracker.browser.service.WebEventService;
import usertracker.browser.service.impl.AnonymousVisitorPhoenixServiceImpl;
import usertracker.browser.service.impl.BrowserFPPhoenixServiceImpl;
import usertracker.browser.service.impl.DeviceFPPhoenixServiceImpl;
import usertracker.browser.service.impl.SessionPhoenixServiceImpl;
import usertracker.browser.service.impl.VisitorLogPhoenixServiceImpl;
import usertracker.browser.service.impl.WebEventPhoenixServiceImpl;

@Configuration
@PropertySource({"classpath:kafka.properties", "classpath:spark.properties"})
public class ServiceConfig {

	@Value("${kafka.topics}")
	private String topics;
	
	@Value("${kafka.metadata.broker.list}")
	private String metaDataBrokerList;
	
	@Autowired
	private PhoenixContext phoenixContext;
	
	@Autowired
	private DaoConfig daoConfig;
	
	@Bean
	public VisitorLogService visitorLogService() throws Exception{
		return new VisitorLogPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	@Bean
	public WebEventService webEventService() throws Exception{
		return new WebEventPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public SessionService sessionService() throws Exception{
		return new SessionPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public AnonymousVisitorService anonymousVisitorService() throws Exception{
		return new AnonymousVisitorPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public BrowserFPService browserFPService() throws Exception{
		return new BrowserFPPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public DeviceFPService deviceFPService() throws Exception{
		return new DeviceFPPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public ReceiverService receiverService() throws Exception {
		return new ReceiverServiceImpl(visitorLogService(), anonymousVisitorService(), webEventService(),
				browserFPService(), deviceFPService(), sessionService());
	}
	
	@Bean
	public RestTemplate restTemplateService(){
		RestTemplate rt = new RestTemplate();

		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		rt.getMessageConverters().add(new StringHttpMessageConverter());
		return rt;
	}
}
