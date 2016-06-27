package com.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.inctool.chart.dao.ChartDao;
import com.inctool.chart.dao.impl.ChartDaoImpl;
import com.inctool.management.dao.MemberDao;
import com.inctool.management.dao.impl.MemberDaoImpl;
import com.inctool.management.dao.impl.UserDaoImpl;
import com.jofel.data.common.dao.JDao;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class DaoContext {
	
	@Autowired
	private MongoContext mongoContext;
	@Bean
	public JDao userDao(){
		return new UserDaoImpl();
	}
	
	@Bean
	public MemberDao memberDao() throws Exception{
		return new MemberDaoImpl(mongoContext.mongoTemplate());
	}
	
	@Bean
	public ChartDao chartDao() throws Exception{
		return new ChartDaoImpl(mongoContext.mongoTemplate());
	}
}
