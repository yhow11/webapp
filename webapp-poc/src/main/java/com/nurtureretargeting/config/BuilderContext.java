package com.nurtureretargeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nurtureretargeting.tracker.visitor.builder.AnonymousVisitorDetailsBuilder;

@Configuration
public class BuilderContext {

	@Autowired
	private ServiceContext serviceContext;
	@Autowired
	private ManagerContext managerContext;
	
	@Bean
	public AnonymousVisitorDetailsBuilder anonymousVisitorDetailsBuilder() throws Exception {
		return new AnonymousVisitorDetailsBuilder(managerContext.anonymousVisitorManager(), serviceContext.browserFPService(), serviceContext.deviceFPService());
	}

}
