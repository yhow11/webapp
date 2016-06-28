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
	private ConfigContext configContext;
	
	@Bean
	public  hbase.dao.HBaseDao HBaseDao() throws Exception {

		return new hbase.dao.impl.HBaseDaoImpl(configContext.hbaseConfig());

	}
	
	@Bean
	public VisitorLogDao visitorLogDao() throws Exception{
		return new VisitorLogDaoImpl(HBaseDao());
	}
}
