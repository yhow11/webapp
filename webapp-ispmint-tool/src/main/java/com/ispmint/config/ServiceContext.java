package com.ispmint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ispmint.authentication.service.IAuthenticationService;
import com.ispmint.authentication.service.impl.AuthenticationServiceImpl;
import com.ispmint.utilities.podio.service.IPodioService;
import com.ispmint.utilities.podio.service.impl.PodioServiceImpl;

@Configuration
@PropertySource("classpath:com/ispmint/properties/application.properties")
public class ServiceContext {
	
	@Bean
	public IPodioService podioService(){
		return new PodioServiceImpl();
	}
	
	@Bean
	public IAuthenticationService authenticationService(){
		return new AuthenticationServiceImpl(podioService());
	}
	
	
}
