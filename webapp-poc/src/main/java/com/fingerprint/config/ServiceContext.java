package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fingerprint.event.service.EventService;
import com.fingerprint.event.service.impl.EventServiceImpl;
import com.fingerprint.keymanagement.service.KeyValueManagementService;
import com.fingerprint.management.service.JUserService;
import com.fingerprint.management.service.impl.UserDetailsServiceImpl;
import com.fingerprint.management.service.impl.UserServiceImpl;
import com.fingerprint.urlmanagement.service.URLManagementService;
import com.fingerprint.util.service.impl.KafkaService;

import service.keymanagement.KeyManagementService;
import service.keymanagement.impl.KeyManagementPhoenixServiceImpl;
import service.urlmanagement.URLImportService;
import service.urlmanagement.URLSiteMapService;
import service.urlmanagement.URLTaggedService;
import service.urlmanagement.impl.URLImportPhoenixServiceImpl;
import service.urlmanagement.impl.URLSiteMapJAXBServiceImpl;
import service.urlmanagement.impl.URLTaggedPhoenixServiceImpl;
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
	private PhoenixContext phoenixContext;
	
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
		return new VisitorLogPhoenixServiceImpl(phoenixContext.sessionFactory());
	}

	@Bean
	public WebEventService webEventService() throws Exception {
		return new WebEventPhoenixServiceImpl(phoenixContext.sessionFactory());
	}

	@Bean
	public SessionService sessionService() throws Exception {
		return new SessionPhoenixServiceImpl(phoenixContext.sessionFactory());
	}

	@Bean
	public AnonymousVisitorService anonymousVisitorService() throws Exception {
		return new AnonymousVisitorPhoenixServiceImpl(phoenixContext.sessionFactory());
	}

	@Bean
	public BrowserFPService browserFPService() throws Exception {
		return new BrowserFPPhoenixServiceImpl(phoenixContext.sessionFactory());
	}

	@Bean
	public DeviceFPService deviceFPService() throws Exception {
		return new DeviceFPPhoenixServiceImpl(phoenixContext.sessionFactory());
	}

	@Bean
	public EventService eventService() throws Exception {
		return new EventServiceImpl(visitorLogService(), anonymousVisitorService(), webEventService(),
				browserFPService(), deviceFPService(), sessionService());
	}

	@Bean
	public KeyManagementService keyManagementService() throws Exception {
		return new KeyManagementPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public KeyValueManagementService keyValueManagementService() throws Exception {
		return new KeyValueManagementService(mapperContext.keyMapper(), keyManagementService());
	}
	
	@Bean
	public URLImportService URLImportService() throws Exception {
		return new URLImportPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public URLTaggedService URLTaggedService() throws Exception {
		return new URLTaggedPhoenixServiceImpl(phoenixContext.sessionFactory());
	}
	
	@Bean
	public URLSiteMapService URLSiteMapService() throws Exception {
		return new URLSiteMapJAXBServiceImpl(context.jaxbDao());
	}
	
	@Bean
	public URLManagementService URLManagementService() throws Exception {
		return new URLManagementService(URLImportService(), URLTaggedService(), URLSiteMapService());
	}
}
