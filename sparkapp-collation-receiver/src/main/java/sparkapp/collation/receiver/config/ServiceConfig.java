package sparkapp.collation.receiver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import service.metricmanagement.MetricService;
import service.metricmanagement.MetricSummaryService;
import service.metricmanagement.impl.MetricSparkSQLServiceImpl;
import service.metricmanagement.impl.MetricSummaryPhoenixServiceImpl;
import service.metricmanagement.model.MetricModel;
import service.pagecount.PageCountService;
import service.pagecount.impl.PageCountSparkSQLServiceImpl;
import service.pagecount.model.PageCountModel;
import service.timeonpage.TimeOnPageService;
import service.timeonpage.impl.TimeOnPageSparkSQLServiceImpl;
import service.urlmanagement.URLMetricService;
import service.urlmanagement.impl.URLMetricSparkSQLServiceImpl;
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
	
	@Value("${kafka.zookeepers}")
	private String zookeepers;
	
	@Autowired
	private PhoenixContext phoenixContext;
	
	@Autowired
	private SparkConfig sparkContext;
	
	@Bean
	public URLMetricService URLMetricService() throws Exception{
		return new URLMetricSparkSQLServiceImpl(sparkContext.sQLContext(), zookeepers);
	}
	
	@Bean
	public TimeOnPageService timeOnPageService() throws Exception{
		return new TimeOnPageSparkSQLServiceImpl(sparkContext.sQLContext(), zookeepers, TimeOnPageModel.class);
	}
	
	@Bean
	public PageCountService pageCountService() throws Exception{
		return new PageCountSparkSQLServiceImpl(sparkContext.sQLContext(), zookeepers, PageCountModel.class);
	}
	
	@Bean
	public MetricSummaryService metricSummaryService() throws Exception{
		return new MetricSummaryPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public MetricService metricService() throws Exception{
		return new MetricSparkSQLServiceImpl(sparkContext.sQLContext(), zookeepers, MetricModel.class);
	}
	
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
