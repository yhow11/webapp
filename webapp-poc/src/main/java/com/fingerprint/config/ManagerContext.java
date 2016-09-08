package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fingerprint.keymanagement.manager.KeyManager;
import com.fingerprint.session.manager.SessionManager;
import com.fingerprint.visitor.manager.AnonymousVisitorManager;

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
	
}
