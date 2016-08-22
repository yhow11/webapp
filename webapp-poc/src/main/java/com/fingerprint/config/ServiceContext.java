package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fingerprint.event.service.EventService;
import com.fingerprint.event.service.impl.EventServiceImpl;
import com.fingerprint.keymanagement.service.KeyValueManagementService;
import com.fingerprint.management.service.JUserService;
import com.fingerprint.management.service.impl.UserDetailsServiceImpl;
import com.fingerprint.management.service.impl.UserServiceImpl;
import com.fingerprint.urltagging.service.URLTaggingService;
import com.fingerprint.urltagging.service.impl.URLTaggingServiceImpl;
import com.fingerprint.util.service.impl.KafkaService;

import service.keymanagement.KeyManagementService;
import service.keymanagement.ValueManagementService;
import service.keymanagement.impl.KeyManagementServiceImpl;
import service.keymanagement.impl.ValueManagementServiceImpl;
import service.urlmanagement.URLManagementService;
import service.urlmanagement.impl.URLManagementServiceImpl;
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
@PropertySource({ "classpath:com/fingerprint/properties/application.properties",
		"classpath:com/fingerprint/properties/kafka.properties" })
public class ServiceContext {

	@Value("${sync}")
	private String sync;

	@Value("${topic}")
	private String topic;

	@Autowired
	private DaoContext context;

	@Autowired
	private ConfigContext configContext;

	@Autowired
	private MapperContext mapperContext;

	@Bean
	public JUserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userService());
	}

	@Bean
	public KafkaService kafkaService() {
		return new KafkaService(configContext.kafkaPropetiers(), sync, topic);
	}

	@Bean
	public VisitorLogService visitorLogService() throws Exception {
		return new VisitorLogServiceImpl(context.phoenixDaoImpl());
	}

	@Bean
	public WebEventService webEventService() throws Exception {
		return new WebEventServiceImpl(context.phoenixDaoImpl());
	}

	@Bean
	public SessionService sessionService() throws Exception {
		return new SessionServiceImpl(context.phoenixDaoImpl());
	}

	@Bean
	public AnonymousVisitorService anonymousVisitorService() throws Exception {
		return new AnonymousVisitorServiceImpl(context.phoenixDaoImpl());
	}

	@Bean
	public BrowserFPService browserFPService() throws Exception {
		return new BrowserFPServiceImpl(context.phoenixDaoImpl());
	}

	@Bean
	public DeviceFPService deviceFPService() throws Exception {
		return new DeviceFPServiceImpl(context.phoenixDaoImpl());
	}

	@Bean
	public EventService eventService() throws Exception {
		return new EventServiceImpl(visitorLogService(), anonymousVisitorService(), webEventService(),
				browserFPService(), deviceFPService(), sessionService());
	}

	@Bean
	public KeyManagementService keyManagementService() throws Exception {
		return new KeyManagementServiceImpl(context.phoenixDaoImpl());
	}
	
	@Bean
	public ValueManagementService valueManagementService() throws Exception {
		return new ValueManagementServiceImpl(context.phoenixDaoImpl());
	}
	
	@Bean
	public KeyValueManagementService keyValueManagementService() throws Exception {
		return new KeyValueManagementService(mapperContext.keyMapper(), mapperContext.valueMapper(), keyManagementService(), valueManagementService());
	}
	
	@Bean
	public URLManagementService URLManagementService() throws Exception {
		return new URLManagementServiceImpl(context.phoenixDaoImpl());
	}
	
	@Bean
	public URLTaggingService URLTaggingService() throws Exception {
		return new URLTaggingServiceImpl(URLManagementService(), valueManagementService(), webEventService(), mapperContext.valueMapper());
	}
}
