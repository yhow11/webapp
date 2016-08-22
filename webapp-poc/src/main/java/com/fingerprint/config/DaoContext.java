package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import helper.phoenix.dao.impl.PhoenixDaoImpl;
import helper.phoenix.dao.impl.SimplePhoenixDaoImpl;

@Configuration
@EnableTransactionManagement
@ComponentScan({"service.keymanagement", "usertracker.browser.service","service.urlmanagement"})
@PropertySource("classpath:com/fingerprint/properties/application.properties")
public class DaoContext {

	@Autowired
	private PhoenixContext phoenixContext;
	
	
	@Bean
	public PhoenixDaoImpl phoenixDaoImpl() throws Exception{
		return new SimplePhoenixDaoImpl(phoenixContext.sessionFactory());
	}
}
