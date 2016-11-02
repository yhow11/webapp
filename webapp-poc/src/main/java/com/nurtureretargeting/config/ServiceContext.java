package com.nurtureretargeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.nurtureretargeting.admin.usermanagement.service.JUserService;
import com.nurtureretargeting.admin.usermanagement.service.impl.UserDetailsServiceImpl;
import com.nurtureretargeting.admin.usermanagement.service.impl.UserServiceImpl;

@Configuration
public class ServiceContext {

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
//
//	@Bean
//	public VisitorLogService visitorLogService() throws Exception {
//		return new VisitorLogPhoenixServiceImpl(phoenixContext.sessionFactory());
//	}
//
//	@Bean
//	public WebEventService webEventService() throws Exception {
//		return new WebEventPhoenixServiceImpl(phoenixContext.sessionFactory());
//	}
//
//	@Bean
//	public SessionService sessionService() throws Exception {
//		return new SessionPhoenixServiceImpl(phoenixContext.sessionFactory());
//	}
//
//	@Bean
//	public AnonymousVisitorService anonymousVisitorService() throws Exception {
//		return new AnonymousVisitorPhoenixServiceImpl(phoenixContext.sessionFactory());
//	}
//
//	@Bean
//	public BrowserFPService browserFPService() throws Exception {
//		return new BrowserFPPhoenixServiceImpl(phoenixContext.sessionFactory());
//	}
//
//	@Bean
//	public DeviceFPService deviceFPService() throws Exception {
//		return new DeviceFPPhoenixServiceImpl(phoenixContext.sessionFactory());
//	}
//
//	
//	@Bean
//	public VisitorRawLogService visitorRawLogService() throws Exception {
//		return new VisitorRawLogKafkaServiceImpl(kafkaContext.kafkaHelper(), "events", true);
//	}
	
}
