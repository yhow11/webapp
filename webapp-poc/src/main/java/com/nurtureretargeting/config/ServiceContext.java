package com.nurtureretargeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nurtureretargeting.admin.urlmanagement.service.URLManagementService;
import com.nurtureretargeting.event.service.EventService;
import com.nurtureretargeting.event.service.impl.EventServiceImpl;
import com.nurtureretargeting.management.service.JUserService;
import com.nurtureretargeting.management.service.impl.UserDetailsServiceImpl;
import com.nurtureretargeting.management.service.impl.UserServiceImpl;

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
import usertracker.browser.service.VisitorRawLogService;
import usertracker.browser.service.WebEventService;
import usertracker.browser.service.impl.AnonymousVisitorPhoenixServiceImpl;
import usertracker.browser.service.impl.BrowserFPPhoenixServiceImpl;
import usertracker.browser.service.impl.DeviceFPPhoenixServiceImpl;
import usertracker.browser.service.impl.SessionPhoenixServiceImpl;
import usertracker.browser.service.impl.VisitorLogPhoenixServiceImpl;
import usertracker.browser.service.impl.VisitorRawLogKafkaServiceImpl;
import usertracker.browser.service.impl.WebEventPhoenixServiceImpl;

@Configuration
public class ServiceContext {

	@Autowired
	private DaoContext context;
	@Autowired
	private PhoenixContext phoenixContext;
	@Autowired
	private KafkaContext kafkaContext;
	
	@Bean
	public JUserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userService());
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
		return new EventServiceImpl(anonymousVisitorService(), webEventService(),
				browserFPService(), deviceFPService(), sessionService());
	}

	@Bean
	public KeyManagementService keyManagementService() throws Exception {
		return new KeyManagementPhoenixServiceImpl(phoenixContext.sessionFactory());
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
	
	@Bean
	public VisitorRawLogService visitorRawLogService() throws Exception {
		return new VisitorRawLogKafkaServiceImpl(kafkaContext.kafkaHelper(), "events", true);
	}
}
