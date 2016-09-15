package com.nurtureretargeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.nurtureretargeting.admin.keymanagement.mapper.KeyMapper;
import com.nurtureretargeting.admin.keymanagement.mapper.ValueMapper;
import com.nurtureretargeting.admin.metricmanagement.mapper.MetricMapper;

@Configuration
@PropertySource("classpath:com/nurtureretargeting/properties/application.properties")
public class MapperContext {
	
	@Bean
	public KeyMapper keyMapper(){
		return new KeyMapper(valueMapper());
	}
	
	@Bean
	public ValueMapper valueMapper(){
		return new ValueMapper();
	}
	

	@Bean
	public MetricMapper metricMapper(){
		return new MetricMapper();
	}
}
