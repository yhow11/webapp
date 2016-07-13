package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import usertracker.browser.dao.VisitorLogDao;
import usertracker.browser.dao.impl.VisitorLogDaoImpl;

@Configuration
@PropertySource("classpath:com/fingerprint/properties/application.properties")
public class DaoContext {

	@Autowired
	private PhoenixContext phoenixContext;
	
	@Bean
	public VisitorLogDao visitorLogDao() throws Exception{
		return new VisitorLogDaoImpl(phoenixContext.defaultDSLContext(), phoenixContext.sessionFactory());
	}
}
