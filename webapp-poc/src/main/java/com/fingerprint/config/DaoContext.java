package com.fingerprint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fingerprint.event.dao.EventDao;
import com.fingerprint.event.dao.impl.EventDaoImpl;

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
	public EventDao eventDao() throws Exception{
		return new EventDaoImpl(HBaseDao());
	}
}
