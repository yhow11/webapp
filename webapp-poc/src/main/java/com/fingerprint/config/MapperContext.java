package com.fingerprint.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:com/fingerprint/properties/application.properties")
public class MapperContext {
	
}
