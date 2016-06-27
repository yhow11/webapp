package com.ispmint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.jofel.mapper.management.JUserMapper;

@Configuration
@PropertySource("classpath:com/ispmint/properties/application.properties")
public class MapperContext {
	
	@Bean
	public JUserMapper userMapper(){
		return new JUserMapper();
	}
	
}
