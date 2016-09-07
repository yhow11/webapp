package com.inctool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.inctool.management.mapper.MemberMapper;
import com.inctool.management.mapper.WorkerMapper;
import com.inctool.management.mapper.impl.MemberMapperImpl;
import com.inctool.management.mapper.impl.WorkerMapperImpl;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class MapperContext {
	
	
	@Bean
	public MemberMapper memberMapper(){
		return new MemberMapperImpl();
	}
	@Bean
	public WorkerMapper workerMapper(){
		return new WorkerMapperImpl();
	}
	
}
