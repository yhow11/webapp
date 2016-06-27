package com.inctool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.inctool.chart.mapper.ChartMapper;
import com.inctool.chart.mapper.impl.ChartMapperImpl;
import com.inctool.management.mapper.MemberMapper;
import com.inctool.management.mapper.impl.MemberMapperImpl;
import com.jofel.mapper.management.JUserMapper;

@Configuration
@PropertySource("classpath:com/inctool/properties/application.properties")
public class MapperContext {
	
	@Bean
	public JUserMapper userMapper(){
		return new JUserMapper();
	}
	
	@Bean
	public MemberMapper memberMapper(){
		return new MemberMapperImpl();
	}
	
	@Bean
	public ChartMapper chartMapper(){
		return new ChartMapperImpl();
	}
}
