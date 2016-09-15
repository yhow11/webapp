package com.nurtureretargeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nurtureretargeting.admin.keymanagement.manager.KeyManager;
import com.nurtureretargeting.admin.metricmanagement.manager.MetricManager;
import com.nurtureretargeting.admin.urlmanagement.manager.URLImportManager;
import com.nurtureretargeting.admin.urlmanagement.manager.URLTaggingManager;
import com.nurtureretargeting.tracker.session.manager.SessionManager;
import com.nurtureretargeting.tracker.visitor.manager.AnonymousVisitorManager;
import com.nurtureretargeting.tracker.webevent.manager.WebEventManager;

@Configuration
public class ManagerContext {

	@Autowired
	private ServiceContext serviceContext;

	@Autowired
	private MapperContext mapperContext;
	
	@Bean
	public AnonymousVisitorManager anonymousVisitorManager() throws Exception {
		return new AnonymousVisitorManager(serviceContext.anonymousVisitorService(), serviceContext.sessionService(), serviceContext.browserFPService());
	}
	
	@Bean
	public SessionManager sessionManager() throws Exception {
		return new SessionManager(anonymousVisitorManager(), serviceContext.sessionService());
	}
	
	@Bean
	public KeyManager keyManager() throws Exception {
		return new KeyManager(mapperContext.keyMapper(), serviceContext.keyManagementService());
	}
	
	@Bean
	public WebEventManager webEventManager() throws Exception {
		return new WebEventManager(serviceContext.webEventService(), anonymousVisitorManager());
	}
	
	@Bean
	public URLTaggingManager URLTaggingManager() throws Exception {
		return new URLTaggingManager(serviceContext.URLImportService(), serviceContext.URLTaggedService());
	}
	
	@Bean
	public URLImportManager URLImportManager() throws Exception {
		return new URLImportManager(serviceContext.URLImportService(), serviceContext.URLSiteMapService());
	}
	
	@Bean
	public MetricManager metricManager() throws Exception {
		return new MetricManager(serviceContext.metricService(), mapperContext.metricMapper());
	}
	
}
