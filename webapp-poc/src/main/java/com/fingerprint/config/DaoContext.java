package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import helper.jaxb.dao.impl.JAXBDao;
import helper.jaxb.dao.impl.JAXBDaoImpl;
import helper.phoenix.dao.impl.PhoenixDaoImpl;
import helper.phoenix.dao.impl.SimplePhoenixDaoImpl;

@Configuration
@PropertySource("classpath:com/fingerprint/properties/jaxb.properties")
public class DaoContext {

	
	
	@Autowired
	private PhoenixContext phoenixContext;
	
	
	@Bean
	public JAXBDao jaxbDao() throws Exception{
		return new JAXBDaoImpl();
	}
	
	@Bean
	public SimplePhoenixDaoImpl simplePhoenixDaoImpl() throws Exception{
		return new SimplePhoenixDaoImpl(phoenixContext.sessionFactory());
	}
}
