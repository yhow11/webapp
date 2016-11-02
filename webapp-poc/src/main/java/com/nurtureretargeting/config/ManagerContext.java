//package com.nurtureretargeting.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.nurtureretargeting.tracker.session.manager.SessionManager;
//import com.nurtureretargeting.tracker.visitor.manager.AnonymousVisitorManager;
//
//@Configuration
//public class ManagerContext {
//
//	@Autowired
//	private ServiceContext serviceContext;
//
//	
//	@Bean
//	public AnonymousVisitorManager anonymousVisitorManager() throws Exception {
//		return new AnonymousVisitorManager(serviceContext.anonymousVisitorService(), serviceContext.sessionService(), serviceContext.browserFPService());
//	}
//	
//	@Bean
//	public SessionManager sessionManager() throws Exception {
//		return new SessionManager(anonymousVisitorManager(), serviceContext.sessionService());
//	}
//	
//	
//}
