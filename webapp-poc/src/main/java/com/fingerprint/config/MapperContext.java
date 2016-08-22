package com.fingerprint.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.fingerprint.keymanagement.mapper.KeyMapper;
import com.fingerprint.keymanagement.mapper.ValueMapper;

@Configuration
@PropertySource("classpath:com/fingerprint/properties/application.properties")
public class MapperContext {
	
	@Bean
	public KeyMapper keyMapper(){
		return new KeyMapper();
	}
	
	@Bean
	public ValueMapper valueMapper(){
		return new ValueMapper();
	}
}
