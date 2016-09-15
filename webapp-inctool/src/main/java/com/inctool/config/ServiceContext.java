package com.inctool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.inctool.management.service.MemberService;
import com.inctool.management.service.impl.MemberServiceImpl;

import service.membermanagement.service.INCMemberService;
import service.membermanagement.service.INCWorkerService;
import service.membermanagement.service.impl.INCMemberMongoServiceImpl;
import service.membermanagement.service.impl.INCWorkerMongoServiceImpl;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class ServiceContext {
	
	@Autowired
	private MongoContext mongoContext;
	
	@Autowired
	private MapperContext mapperContext;
	
	@Bean
	public MemberService memberService() throws Exception{
		return new MemberServiceImpl(incMemberService(), mapperContext.memberMapper());
	}
	
	@Bean
	public INCMemberService incMemberService() throws Exception{
		return new INCMemberMongoServiceImpl(mongoContext.mongoTemplate());
	}
	
	@Bean
	public INCWorkerService incWorkerService() throws Exception{
		return new INCWorkerMongoServiceImpl(mongoContext.mongoTemplate());
	}
}
